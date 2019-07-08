package com.trilogyed.bookservice.model;

import com.trilogyed.bookservice.util.notes.Note;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class BookViewModel {
    private int bookId;
    @Size(max = 50)
    @NotEmpty(message = "Please provide the title of the book.")
    private String title;
    @Size(max = 50)
    @NotEmpty(message = "Please provide the author name.")
    private String author;
    @Valid
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel that = (BookViewModel) o;
        return bookId == that.bookId &&
                title.equals(that.title) &&
                author.equals(that.author) &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, notes);
    }
}
