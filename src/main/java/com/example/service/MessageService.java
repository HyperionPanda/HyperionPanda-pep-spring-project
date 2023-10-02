package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.entity.Message;
import com.example.repository.MessageRepository;



@Component
public class MessageService {

    MessageRepository repository;

    @Autowired
    public MessageService(MessageRepository newRepository){
        repository = newRepository;
    }

    //needs to test for if user is a real person, doesnt do that currently
    public Message createMessage(Message message){
        if (!(message.getMessage_text().isBlank()) && message.getMessage_text().length() < 255 ){
            return repository.newMessage(message.getMessage_id(),message.getMessage_text(),message.getTime_posted_epoch());
    
        }else{
            return null;
        }
    }

    public List<Message> getAllMessages(){
        return repository.allMessages();
    }

    public Message getOneMessageById(Integer id){
        return repository.oneMessagebyMessageId(id);
    }

    //in controller body should be blank if no message existed, should test for?
    public Integer deleteMsgById(Integer id){
        return repository.deleteMessagebyId(id);
    }

    // needs to get existing message before calling update since it requires all information
    //or change repository to only require the id and the message text
    //if doing second option, test for if it tries to change other info during test, since not garunteed but possible
    /* 
    public Integer updateMsgById(Integer id){
        return repository.updateMessagebyId(id,)
    }
    */

    public List<Message> getAllMsgsByUser(Integer user_id){
        return repository.allMessagesbyUser(user_id);
    }
}
