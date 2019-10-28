package model;

import java.nio.charset.Charset;

public class Message {

    public String userId;
    public String content;

    public Message(String userId, String content) {
        this.userId = userId;
        this.content = new String(content.getBytes(), Charset.forName("UTF-8"));
    }

    @Override
    public String toString() {
        return userId + ": " + content;
    }

}
