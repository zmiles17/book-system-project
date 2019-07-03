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
    @RequestMapping(value = "/note", method = RequestMethod.POST)
    public Note createNote(@RequestBody Note note);

    @RequestMapping(value = "/note/{id}", method = RequestMethod.PUT)
    public void updateNote(@RequestBody Note note, @PathVariable("id") int id);

    @RequestMapping(value = "/note", method = RequestMethod.GET)
    public List<Note> getAllNotes();

    @RequestMapping(value = "/note/{id}", method = RequestMethod.GET)
    public Note getNote(@PathVariable("id") int id);
}
