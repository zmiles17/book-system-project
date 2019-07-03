package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.exception.NotFoundException;
import com.trilogyed.bookservice.model.BookViewModel;
import com.trilogyed.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
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

    @PutMapping("/{bookId}")//Another way to set the Rest API Put mapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable("bookId") int bookId, @RequestBody @Valid BookViewModel bookViewModel) {
        if (bookViewModel.getBook_id() == 0)
            bookViewModel.setBook_id(bookId);
        if (bookId != bookViewModel.getBook_id()) {
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
