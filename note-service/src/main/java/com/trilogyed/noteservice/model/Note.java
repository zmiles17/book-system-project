package com.trilogyed.noteservice.model;

import javax.validation.constraints.*;
import java.util.Objects;

public class Note {

    private int noteId;
    @Min(value = 1, message = "Must have a valid book Id")
    private int bookId;
    @Size(max = 255, message = "Note must be less than 255 characters")
    private String note;

    public Note() {
    }

    public Note(int noteId, int bookId, String note) {
        this.noteId = noteId;
        this.bookId = bookId;
        this.note = note;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", bookId=" + bookId +
                ", note='" + note + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return noteId == note1.noteId &&
                bookId == note1.bookId &&
                Objects.equals(note, note1.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, bookId, note);
    }
}
