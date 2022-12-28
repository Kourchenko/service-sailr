package com.kourchenko.mongodb.dao;

public class AccountHttpStatus {
    private boolean is2xxSuccessful;
    private int value;
    private String reasonPhrase;

    public AccountHttpStatus(boolean is2xxSuccessful, int value, String reasonPhrase) {
        this.is2xxSuccessful = is2xxSuccessful;
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public boolean isIs2xxSuccessful() {
        return this.is2xxSuccessful;
    }

    public void setIs2xxSuccessful(boolean is2xxSuccessful) {
        this.is2xxSuccessful = is2xxSuccessful;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

}
