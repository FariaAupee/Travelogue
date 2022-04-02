package com.example.mylogin;

public class BookingClass {

    private String place;
    private  String name;
    private String mail;
    private  String date;
    private  String people;

    public BookingClass()
    {

    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public BookingClass(String place, String name, String mail, String date, String people) {
        this.place = place;
        this.name = name;
        this.mail = mail;
        this.date = date;
        this.people = people;
    }
}
