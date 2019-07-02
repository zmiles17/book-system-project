package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDaoTests {

    @Autowired
    BookDao dao;

    @Before
    public void setUp() throws Exception {

        List<Book> books = dao.getAllBooks();

        books.stream().forEach(book -> dao.deleteBook(book.getBook_id()));

    }

    @Test
    public void addGetDeleteBook() {

        Book book = new Book(2, "Title", "Author");

        book = dao.addBook(book);

        Book fromDao = dao.getBook(book.getBook_id());

        assertEquals(fromDao, book);

        dao.deleteBook(book.getBook_id());

        fromDao = dao.getBook(book.getBook_id());

        assertNull(fromDao);

    }

    @Test
    public void getAllBooks() {

        Book book = new Book(2, "Title", "Author");
        dao.addBook(book);

        book = new Book(5, "Another Title", "Another Author");
        dao.addBook(book);

        List<Book> books = dao.getAllBooks();

        assertEquals(2, books.size());

    }

    @Test
    public void updateBook() {

        Book book = new Book(2, "Title", "Author");
        book = dao.addBook(book);

        book.setTitle("Different Title");
        book.setAuthor("Different Author");

        dao.updateBook(book);
        Book fromDao = dao.getBook(book.getBook_id());

        assertEquals(book, fromDao);

    }

}
