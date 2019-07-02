package com.trilogyed.bookservice.model;

public class Book {

    private int book_id;
    private String title;
    private String author;

    public Book() {}

    public Book(int book_id, String title, String author) {

        this.book_id = book_id;
        this.title = title;
        this.author = author;

    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
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
        return book_id == book.book_id &&
                title.equals(book.title) &&
                author.equals(book.author);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), book_id, title, author);
    }

}
