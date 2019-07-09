package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.dao.BookDaoJdbcTemplateImpl;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.BookViewModel;
import com.trilogyed.bookservice.util.feign.NoteClient;
import com.trilogyed.bookservice.util.notes.Note;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

public class BookServiceTests {

    BookDao bookDao;
    NoteClient noteClient = mock(NoteClient.class);
    RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
    BookService bookService;

    @Before
    public void setUp() throws Exception {

        setUpBookDaoMock();
        setUpRestClientMock();
        bookService = new BookService(noteClient, bookDao, rabbitTemplate);

    }

    private void setUpBookDaoMock() {

        bookDao = mock(BookDaoJdbcTemplateImpl.class);

        Book book = new Book();
        book.setBookId(4);
        book.setAuthor("Author");
        book.setTitle("Title");

        Book book1 = new Book();
        book1.setAuthor("Author");
        book1.setTitle("Title");
        book1.setBookId(2);

        Book book2 = new Book();
        book2.setAuthor("Author");
        book2.setTitle("Title");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book);

        doReturn(book1).when(bookDao).addBook(book2);
        doReturn(book1).when(bookDao).getBook(2);

        doReturn(bookList).when(bookDao).getAllBooks();

    }

    private void setUpRestClientMock() {

       // noteClient = mock(NoteClient.class);

        Note note = new Note();
        note.setNote("yes!");
        note.setBookId(2);
        note.setNoteId(1);

        List<Note> noteList = new ArrayList<>();
        noteList.add(note);

        doReturn(noteList).when(noteClient).getNotesByBook(2);

    }

    @Test
    public void newBook() {

        List<Note> noteList = new ArrayList<>();

        Note note = new Note();
        note.setBookId(2);
        note.setNote("yes!");
        note.setNoteId(1);

        noteList.add(note);

        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setBookId(2);
        bookViewModel.setTitle("Title");
        bookViewModel.setAuthor("Author");
        bookViewModel.setNotes(noteList);

        bookViewModel = bookService.newBook(bookViewModel);

        BookViewModel fromService = bookService.fetchBook(bookViewModel.getBookId());
        assertEquals(bookViewModel, fromService);

    }

    @Test
    public void fetchAllBook() {

        List<BookViewModel> bookList = bookService.fetchAllBooks();
        System.out.println(bookList);

        assertEquals(2, bookList.size());

    }

}
