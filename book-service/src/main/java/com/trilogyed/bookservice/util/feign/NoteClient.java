package com.trilogyed.bookservice.util.feign;

import com.trilogyed.bookservice.util.notes.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "note-service")
public interface NoteClient {
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public Note createNote(@RequestBody Note note);

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    public void updateNote( @RequestBody Note note,@PathVariable("id") int id);

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public void getAllNotes();

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    public void getnote(@PathVariable("id") int id);
}
