package com.mohammed.quran_tele_bot.services;

import com.mohammed.quran_tele_bot.models.User;
import com.mohammed.quran_tele_bot.repositories.UserRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramBotService {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotService.class);

    private final UserRepository userRepository;
    private final TelegramBot bot;
    private final VerseService verseService;

    public TelegramBotService(UserRepository userRepository, TelegramBot bot, VerseService verseService) {
        this.userRepository = userRepository;
        this.bot = bot;
        this.verseService = verseService;
    }

    public void handleNewMessages() {
        GetUpdates getUpdates = new GetUpdates();
        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        List<Update> updates = updatesResponse.updates();
        int last_update_id = updates.isEmpty() ? 0 : updates.get(updates.size() - 1).updateId() + 1;
        while (!updates.isEmpty()) {
            for (Update update : updates) {
                Message message = update.message();
                if (message == null) continue;
                String messageText = message.text();
                String chatId = message.chat().id().toString();
                if (messageText.equals("/start")) {
                    addNewUser(chatId);
                } else {
                    sendReminderMessage(chatId);
                }
            }
            getUpdates = new GetUpdates().offset(last_update_id);
            updatesResponse = bot.execute(getUpdates);
            updates = updatesResponse.updates();
            last_update_id = updates.isEmpty() ? 0 : updates.get(updates.size() - 1).updateId() + 1;
        }
    }

    public void sendVerse() {
        List<User> users = userRepository.findAllUsers();
        String verse = verseService.getRandomVerse();

        if(verse == null){
            logger.error("Error happened in getting random verse");
            return;
        }

        for (User user : users) {
            SendResponse response = bot.execute(new SendMessage(user.getChatId(), verse));
        }
    }

    public void addNewUser(String chatId) {
        User user = new User();
        user.setChatId(chatId);
        userRepository.storeUser(user);
        sendWelcomeMessage(chatId);
    }

    public void sendWelcomeMessage(String chatId) {
        SendResponse response = bot.execute(new SendMessage(chatId, "السلام عليكم" +
                "\n" +
                "سيتم ارسال ايات من القرآن الكريم بشكل دوري."));
    }

    public void sendReminderMessage(String chatId) {
        SendResponse response = bot.execute(new SendMessage(chatId,
                "سيتم ارسال ايات من القرآن الكريم بشكل دوري."));
    }


}
