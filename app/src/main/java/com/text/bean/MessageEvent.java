package com.text.bean;

/**
 * Created by ZCYL on 2017/5/16.
 */

public class MessageEvent {
    private String name,message;

    public MessageEvent() {
    }

    public MessageEvent(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
