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
import java.util.stream.Collectors;

@Component
public class BookService {
    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.#";

    private final NoteClient client;

    BookDao dao;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public BookService(NoteClient client, BookDao dao, RabbitTemplate rabbitTemplate) {
        this.client = client;
        this.dao = dao;
        this.rabbitTemplate = rabbitTemplate;
    }

    public BookViewModel newBook(BookViewModel bookViewModel) {
        Book book = new Book();
        book.setTitle(bookViewModel.getTitle());
        book.setAuthor(bookViewModel.getAuthor());
        final Book bookReceived = dao.addBook(book);
        bookViewModel.setBookId(bookReceived.getBookId());

        Note note = new Note();
        List<Note> notes = bookViewModel.getNotes();
            notes.forEach(e -> {
                note.setBookId(bookViewModel.getBookId());
                note.setNote(e.getNote());
                System.out.println("Sending note...");
                rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, note);
                System.out.println("Note Sent");
            });

            try {
                Thread.sleep(1000);
                List<Note> notesFromService =
                        client.getAllNotes()
                            .stream()
                            .filter(n -> n.getBookId() == bookReceived.getBookId())
                            .collect(Collectors.toList());
                bookViewModel.setNotes(notesFromService);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
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

    public void deleteBook(int id) {
        dao.deleteBook(id);
    }

    public void updateBook(BookViewModel bookViewModel) {
        Book book = new Book(
                bookViewModel.getBookId(),
                bookViewModel.getTitle(),
                bookViewModel.getAuthor()
        );
        bookViewModel.getNotes().forEach(note -> {
            note.setBookId(book.getBookId());
            client.updateNote(note.getNoteId(), note);
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, note);
        });
        dao.updateBook(book);
    }

    private BookViewModel buildBookViewModel(Book book) {
        BookViewModel bvm = new BookViewModel();
        bvm.setBookId(book.getBookId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());
        List<Note> noteList = client.getAllNotes();
        if(noteList.size() != 0) {
            noteList = noteList
                    .stream()
                    .filter(note -> note.getBookId() == book.getBookId())
                    .collect(Collectors.toList());
            bvm.setNotes(noteList);
        }
        return bvm;
    }
}
