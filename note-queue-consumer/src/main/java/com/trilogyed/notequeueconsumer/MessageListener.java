package com.trilogyed.notequeueconsumer;

import com.trilogyed.notequeueconsumer.util.feign.NoteClient;
import com.trilogyed.notequeueconsumer.util.messages.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    private final NoteClient client;

    @Autowired
    MessageListener(NoteClient client) {
        this.client = client;
    }

    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveNote(Note note) {
        try {
            if (note.getNoteId() == 0) {
                Note fromClient = client.createNote(note);
                System.out.println("Note created: " + fromClient.getNote());
            } else if (note.getNoteId() > 0) {
                client.updateNote(note.getNoteId(), note);
                System.out.println("Note updated: " + note.getNote());
            }
        } catch (Exception e) {
            System.out.println(note.getNote() + " " + note.getBookId());
            System.out.println("Exception occurred:" + e.getMessage());
        }
    }
}
