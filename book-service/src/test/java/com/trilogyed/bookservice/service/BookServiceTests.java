package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.dao.BookDaoJdbcTemplateImpl;
import com.trilogyed.bookservice.model.Book;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

public class BookServiceTests {

    BookDao bookDao;

    @Before
    public void setUp() throws Exception {

        setUpBookDaoMock();

    }

    private void setUpBookDaoMock() {

        bookDao = mock(BookDaoJdbcTemplateImpl.class);

        Book book1 = new Book(2, "Title", "Author");

        Book book2 = new Book(2, "Title", "Author");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);

        doReturn(book1).when(bookDao).addBook(book2);
        doReturn(book1).when(bookDao).getBook(2);

        doReturn(bookList).when(bookDao).getAllBooks();

    }

    private void saveBook() {

        Book book = new Book(5, "Title", "Author");

        book = BookService.saveBook(book);
        Book fromService = BookService.getBook(book.getBook_id());
        assertEquals(book, fromService);

    }

    private void getBooksByAuthor() {

        

    }

}
