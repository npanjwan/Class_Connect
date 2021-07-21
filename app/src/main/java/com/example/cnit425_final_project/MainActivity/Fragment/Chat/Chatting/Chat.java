/**
 * Chat.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Chat.Chatting;

public class Chat {
    private String sender;
    private String message;
    private String group;
    private String sender_name;

    public Chat(String sender, String message, String group, String sender_name) {
        this.sender = sender;
        this.message = message;
        this.group = group;
        this.sender_name = sender_name;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }
}
