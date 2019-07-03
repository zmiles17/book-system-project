package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;

import java.util.List;

public interface NoteDao {

    Note addNote(Note note);

    Note getNote(int id);

    Note getNoteByBook(int id);

    List<Note> getAllNotes();

    Note updateNote(Note note);

    void deleteNote(int id);
}