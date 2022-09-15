package com.mohammed.quran_tele_bot.models;

public class User {
    private long id;
    private String chatId;

    public void setId(long id) {
        this.id = id;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public long getId() {
        return id;
    }

    public String getChatId() {
        return chatId;
    }
}
