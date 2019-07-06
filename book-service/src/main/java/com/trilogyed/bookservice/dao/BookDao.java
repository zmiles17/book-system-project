package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;

import java.util.List;

public interface BookDao {

    public Book addBook(Book book);

    public List<Book> getAllBooks();

    public Book getBook(int bookId);

    public void updateBook(Book book);

    public void deleteBook(int bookId);

}
