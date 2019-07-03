package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.BookViewModel;
import com.trilogyed.bookservice.util.feign.NoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookService {

//    private final NoteClient client

    BookDao dao;

    @Autowired
    public BookService(BookDao dao /*,NoteClient client*/) {
        this.dao = dao;
//        this.client = client;
    }

    public BookViewModel newBook(BookViewModel bookViewModel) {
        Book book = new Book(
                bookViewModel.getBook_id(),
                bookViewModel.getTitle(),
                bookViewModel.getAuthor()
                );
        book = dao.addBook(book);
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
