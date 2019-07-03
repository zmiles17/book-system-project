package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoTest {

    @Autowired
    protected NoteDao dao;

    @Before
    public void setUp() throws Exception {
        List<Note> allNotes = dao.getAllNotes();

        allNotes.stream()
                .forEach(note -> dao.deleteNote(note.getNoteId()));
    }

    @Test
    public void addGetDeleteNote() {
        Note note = generateTestNote1();
        note = dao.addNote(note);

        Note note2 = dao.getNote(note.getNoteId());

        assertEquals(note, note2);

        dao.deleteNote(note.getNoteId());

        note2 = dao.getNote(note.getNoteId());

        assertNull(note2);
    }

    @Test
    public void getNoteByBook() {
        Note note = generateTestNote1();
        dao.addNote(note);

        Note note2 = dao.getNoteByBook(42);

        assertEquals(note, note2);
    }

    @Test
    public void getAllNotes() {
        Note note1 = generateTestNote1();
        Note note2 = generateTestNote2();
        Note note3 = generateTestNote3();
        dao.addNote(note1);
        dao.addNote(note2);
        dao.addNote(note3);

        List<Note> noteList = dao.getAllNotes();

        assertEquals(3, noteList.size());
    }

    @Test
    public void updateNote() {
        Note note = generateTestNote1();
        note = dao.addNote(note);

        note.setBookId(999);
        note.setNote("NEW AND UPDATED TEST NOTE");

        dao.updateNote(note);

        Note note2 = dao.getNote(note.getNoteId());

        assertEquals(note, note2);
    }

    public Note generateTestNote1(){
        Note note = new Note();
        note.setBookId(42);
        note.setNote("This book note is fully functional;");
        return note;
    }

    public Note generateTestNote2(){
        Note note = new Note();
        note.setBookId(88);
        note.setNote("I am Note #2.  Why am I here?");
        return note;
    }

    public Note generateTestNote3(){
        Note note = new Note();
        note.setBookId(20);
        note.setNote("Now we have a List of notes.  Booyah.");
        return note;
    }
}