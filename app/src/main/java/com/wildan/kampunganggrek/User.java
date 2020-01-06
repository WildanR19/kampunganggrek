package com.wildan.kampunganggrek;

public class User {
    public int id;
    public String userName;
    public String email;
    public String password;

    public User(int id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String username) {
        this.userName = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

