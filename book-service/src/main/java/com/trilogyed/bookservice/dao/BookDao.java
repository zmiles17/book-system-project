package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;

import java.util.List;

public interface BookDao {

    public Book addBook(Book book);

    public List<Book> getAllBooks();

    public Book getBook(int book_id);

    public Book updateBook(Book book);

    public Book deleteBook(int book_id);

}
