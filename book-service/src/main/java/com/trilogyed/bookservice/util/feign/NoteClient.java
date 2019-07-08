package com.trilogyed.bookservice.util.feign;

import com.trilogyed.bookservice.util.notes.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-service")
public interface NoteClient {

    @GetMapping
    List<Note> getAllNotes();

    @GetMapping("{id}")
    Note getNote(@PathVariable("id") int id);

    @GetMapping("book/{book_id}")
    List<Note> getNotesByBook(@PathVariable("book_id") int bookId);

    @DeleteMapping("{id}")
    void deleteNote(@PathVariable("id") int id);
}
