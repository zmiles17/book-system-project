package com.trilogyed.bookservice.util.notes;

import javax.validation.constraints.Size;

public class Note {

    private int noteId;
    private int bookId;
    @Size(max = 255, message = "Note must be less than 255 characters")
    private String note;

    public Note() {
    }
    public Note(int bookId, String note) {
        this.bookId = bookId;
        this.note = note;
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
}
