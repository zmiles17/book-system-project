package com.trilogyed.bookservice.model;

public class Book {

    private int bookId;
    private String title;
    private String author;

    public Book() {}

    public Book(int bookId, String title, String author) {

        this.bookId = bookId;
        this.title = title;
        this.author = author;

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Book book = (Book) object;
        return bookId == book.bookId &&
                title.equals(book.title) &&
                author.equals(book.author);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), bookId, title, author);
    }

}
