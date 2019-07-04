package com.trilogyed.bookservice.util.feign;

import com.trilogyed.bookservice.util.notes.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "note-service", url = "http://localhost:1984")
public interface NoteClient {
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public Note createNote(@RequestBody Note note);

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    public void updateNote(@PathVariable("id") int id, @RequestBody Note note);

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<Note> getAllNotes();

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    public Note getNote(@PathVariable("id") int id);
}
