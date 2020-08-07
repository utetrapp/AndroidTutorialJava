package de.h_da.fbi.demofirebase;

import java.util.Date;

public class Message {
    private String content;
    private Date timestamp;
    private String userId;
    private String nickname;

    public Message(){}
    public Message(String content, String userId, String nickname){
        setTimestamp(new Date());
        this.setContent(content);
        this.setUserId(userId);
        this.setNickname(nickname);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
