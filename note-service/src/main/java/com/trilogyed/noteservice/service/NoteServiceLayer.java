package com.trilogyed.noteservice.service;

import com.trilogyed.noteservice.dao.NoteDao;
import com.trilogyed.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteServiceLayer {
    @Autowired
    private NoteDao noteDao;

    public void updateNote(int noteId, Note note) {
        List<Note> notesById = noteDao.getNotesByBook(note.getBookId());
        boolean hasNote = false;
        for(Note n : notesById) {
            if (n.getNoteId() == noteId) hasNote = true;
        }
        if(notesById.size() != 0 && hasNote) {
            noteDao.updateNote(noteId, note);
        } else {
            throw new RuntimeException("That note cannot be updated.");
        }
    }
}
