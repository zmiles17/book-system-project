package com.trilogyed.bookservice.model;

import com.trilogyed.bookservice.util.notes.Note;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class BookViewModel {
    private int bookId;
    @Size(max = 50)
    @NotEmpty(message = "Please provide the title of the book.")
    private String title;
    @Size(max = 50)
    @NotEmpty(message = "Please provide the author name.")
    private String author;
    private List<Note> notes;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
