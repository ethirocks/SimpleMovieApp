package com.example.movieapplication;

public class User {

    private String email;
    private String number;
    private String bdate;
    private String password;

    public User() {}
    public User(String email, String number, String bdate, String password) {
        this.email = email;
        this.number = number;
        this.bdate = bdate;
        this.password =password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
