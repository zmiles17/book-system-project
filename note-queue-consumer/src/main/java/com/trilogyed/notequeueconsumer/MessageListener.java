package com.trilogyed.notequeueconsumer;

import com.trilogyed.notequeueconsumer.util.feign.NoteClient;
import com.trilogyed.notequeueconsumer.util.messages.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    @Autowired
    private final NoteClient client;


    MessageListener(NoteClient client) {
        this.client = client;
    }

    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Note note) {
       if(note.getNoteId()==0){
             client.addNote(note);
           System.out.println(note.toString());
       }
       else
       {
            client.updateNote(note,note.getNoteId());
           System.out.println(note.toString());
       }
    }

}
