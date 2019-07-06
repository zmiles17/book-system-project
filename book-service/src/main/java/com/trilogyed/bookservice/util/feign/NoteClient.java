package com.trilogyed.bookservice.util.feign;

import com.trilogyed.bookservice.util.notes.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-service", path = "/notes")
public interface NoteClient {
    @PostMapping
    public Note createNote(@RequestBody Note note);

    @PutMapping(value = "{id}")
    public void updateNote(@PathVariable("id") int id, @RequestBody Note note);

    @GetMapping
    public List<Note> getAllNotes();

    @GetMapping(value = "{id}")
    public Note getNote(@PathVariable("id") int id);

    @GetMapping(value = "book/{book_id}")
    public List<Note> getNotesByBook(@PathVariable("book_id") int bookId);
}
