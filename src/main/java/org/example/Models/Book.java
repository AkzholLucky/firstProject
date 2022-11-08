package org.example.Models;

public class Book {
    private int id;
    private int personId;
    private String name;
    private String authorName;
    private int year;

    public Book(){}

    public Book(int id, int personId, String name, String authorName, int year) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.authorName = authorName;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
