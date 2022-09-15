package com.mohammed.quran_tele_bot.models;

public class VerseData  {
    private int number;
    private String text;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "VerseData{" +
                "number=" + number +
                ", text='" + text + '\'' +
                '}';
    }
}
