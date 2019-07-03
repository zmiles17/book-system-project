package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.dao.BookDaoJdbcTemplateImpl;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.BookViewModel;
import com.trilogyed.bookservice.util.feign.NoteClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

public class BookServiceTests {

    BookDao bookDao;
    NoteClient noteClient;
    BookService bookService;

    @Before
    public void setUp() throws Exception {

        this.bookService = new BookService(bookDao);
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

    @Test
    public void newBook() {

        BookViewModel book = new BookViewModel();

        book = bookService.newBook(book);
        BookViewModel fromService = bookService.fetchBook(book.getBook_id());
        assertEquals(book, fromService);

    }


}
