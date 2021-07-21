/**
 * Chats.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.MainActivity.Fragment.Chat;

public class Chats {
    private String display_name;
    private String chat_type;

    public Chats(String display_name, String chat_type) {
        this.display_name = display_name;
        this.chat_type = chat_type;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getChat_type() {
        return chat_type;
    }

    public void setChat_type(String chat_type) {
        this.chat_type = chat_type;
    }
}
