package com.trilogyed.noteservice.controller;

import com.trilogyed.noteservice.dao.NoteDao;
import com.trilogyed.noteservice.model.Note;
import com.trilogyed.noteservice.service.NoteServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    NoteDao noteDao;

    @Autowired
    NoteServiceLayer service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note addNote(@RequestBody @Valid Note note) {
        return noteDao.addNote(note);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Note getNote(@PathVariable("id") int id) {
        return noteDao.getNote(id);
    }

    @GetMapping("/book/{book_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNotesByBook(@PathVariable("book_id") int id) {
        return noteDao.getNotesByBook(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotes() {
        return noteDao.getAllNotes();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNote(@PathVariable("id") int noteId,
                           @RequestBody @Valid Note note) {
        service.updateNote(noteId, note);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable("id") int id) {
        noteDao.deleteNote(id);
    }
}
