package com.example.recyclefirebaseretrieve;

public class User {

    String firstName, lastName, age, username,date, daysleft,tokens,id;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDate() {
        return date;
    }
    public String getTokens(){return tokens;}
    public String getAge() {
        return age;
    }
    public String getDaysleft() {
        return daysleft;
    }
    public String getID() {
        return id;
    }
    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public void setUserName(String username) {
        this.username = username;
    }
    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public void User1(String firstName, String lastName, String age, String id,String date, String daysleft, String tokens) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.date = date;
        this.daysleft = daysleft;
        this.tokens = tokens;
        this.id = id;
    }

}