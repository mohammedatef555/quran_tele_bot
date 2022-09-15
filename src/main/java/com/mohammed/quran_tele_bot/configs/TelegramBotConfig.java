package com.mohammed.quran_tele_bot.configs;

import com.mohammed.quran_tele_bot.services.TelegramBotService;
import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TelegramBotConfig {

    @Autowired
    private @Lazy TelegramBotService telegramBotService;

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot("bot_token");
    }

    @Scheduled(fixedDelay = 1000)
    public void runHandleNewMessages() {
            telegramBotService.handleNewMessages();
    }

    @Scheduled(fixedDelay = 1_800_000)
    public void runSendVerse() {
        telegramBotService.sendVerse();
    }
}
