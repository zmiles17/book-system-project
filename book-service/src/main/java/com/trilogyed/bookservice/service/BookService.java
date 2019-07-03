package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.BookViewModel;
import com.trilogyed.bookservice.util.feign.NoteClient;
import com.trilogyed.bookservice.util.notes.Note;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookService {
    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.#";

    @Autowired
    private NoteClient client;

    BookDao dao;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public BookService(BookDao dao) {
        this.dao = dao;
    }

    public BookService(NoteClient client) {
        this.client = client;
    }

    public BookService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public BookViewModel newBook(BookViewModel bookViewModel) {
        Book book = new Book(
                bookViewModel.getBook_id(),
                bookViewModel.getTitle(),
                bookViewModel.getAuthor()
                );
        book = dao.addBook(book);
        //call note service through rabbitq to add a note
        String notes ="New book added";
        Note note = new Note(book.getBook_id(),notes);
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, note);
        System.out.println("Message Sent");
        ///////////////////////////
        bookViewModel.setBook_id(book.getBook_id());
        return bookViewModel;
    }

    public BookViewModel fetchBook(int id) {
        try {
            Book book = dao.getBook(id);
            return buildBookViewModel(book);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public List<BookViewModel> fetchAllBooks() {
        List<Book> books = dao.getAllBooks();
        List<BookViewModel> bvmList = new ArrayList<>();
        books.forEach(book -> {
            bvmList.add(buildBookViewModel(book));
        });
        return bvmList;
    }

    public BookViewModel deleteBook(int id) {
        BookViewModel bvm = buildBookViewModel(dao.getBook(id));
        dao.deleteBook(id);
        return bvm;
    }

    public BookViewModel updateBook(BookViewModel bookViewModel) {
        Book book = new Book(
                bookViewModel.getBook_id(),
                bookViewModel.getTitle(),
                bookViewModel.getAuthor()
        );
        return buildBookViewModel(dao.updateBook(book));
    }

    private BookViewModel buildBookViewModel(Book book) {
        BookViewModel bvm = new BookViewModel();
        bvm.setBook_id(book.getBook_id());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());
//        bvm.setNotes();
        return bvm;
    }

}
