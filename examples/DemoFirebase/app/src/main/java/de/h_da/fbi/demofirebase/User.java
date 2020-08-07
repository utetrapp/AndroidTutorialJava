package de.h_da.fbi.demofirebase;

public class User {
    //more fields possible
    private String nickname="anonym";
    public User(String nickname){this.nickname = nickname;}
    public User(){}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
