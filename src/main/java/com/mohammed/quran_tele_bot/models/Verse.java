package com.mohammed.quran_tele_bot.models;

public class Verse {
    private int code;
    private VerseData data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(VerseData data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public VerseData getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Verse{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
