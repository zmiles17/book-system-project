package com.trilogyed.noteservice.service;

import com.trilogyed.noteservice.dao.NoteDao;
import com.trilogyed.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteServiceLayer {
    @Autowired
    private NoteDao noteDao;

    public void updateNote(int noteId, Note note) {
            Note noteReceived = noteDao.getNote(noteId);
            if(noteReceived != null) {
                if(note.getBookId() == 0) note.setBookId(noteReceived.getBookId());
                noteDao.updateNote(noteId, note);
            } else {
                // Throw an exception and return a message if the note does not exist.
            }
    }
}
