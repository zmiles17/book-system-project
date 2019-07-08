package com.trilogyed.notequeueconsumer.util.feign;

import com.trilogyed.notequeueconsumer.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "note-service", path = "/notes")
public interface NoteClient {
    @PostMapping
    Note createNote(@RequestBody Note note);

    @PutMapping("{id}")
    void updateNote(@PathVariable("id") int noteId, @RequestBody Note note);
}
