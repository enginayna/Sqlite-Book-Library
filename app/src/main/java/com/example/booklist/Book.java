package com.example.booklist;

public class Book {
    private int ID;
    private String bookName;
    private String authorName;

    public Book(){

    }

    public Book(String bookName, String authorName) {
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public Book(int ID, String bookName, String authorName) {
        this.ID = ID;
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
