package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.exception.NotFoundException;
import com.trilogyed.bookservice.model.BookViewModel;
import com.trilogyed.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookViewModel addBook(@RequestBody @Valid BookViewModel book) {
        return bookService.newBook(book);
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBook(@PathVariable("bookId") int bookId) {
        BookViewModel bookViewModel = bookService.fetchBook(bookId);
        if (bookViewModel == null)
            throw new NotFoundException("Book could not be retrieved for id " + bookId);
        return bookViewModel;
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("bookId") int bookId) {
        bookService.deleteBook(bookId);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable("bookId") int bookId, @RequestBody @Valid BookViewModel bookViewModel) {
        if (bookViewModel.getBookId() == 0)
            bookViewModel.setBookId(bookId);
        if (bookId != bookViewModel.getBookId()) {
            throw new IllegalArgumentException("Book ID on path must match the ID in the Book object");
        }
        bookService.updateBook(bookViewModel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> getAllBooks() {
        return bookService.fetchAllBooks();

    }
}
