package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.stereotype.Service;



@Service
public class MessageService {

    MessageRepository repository;
    AccountRepository userRepository;

    @Autowired
    public MessageService(MessageRepository newRepository, AccountRepository userrepository){
        repository = newRepository;
        userRepository = userrepository;
    }

    //needs to test for if user is a real person, doesnt do that currently
    public Message createMessage(Message message){
        if (message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && userRepository.existsById(message.getPosted_by())){
            //return repository.newMessage(message.getMessage_id(),message.getMessage_text(),message.getTime_posted_epoch());
            return repository.save(message);
        }else{
            return null;
        }
    }
/*
    public List<Message> getAllMessages(){
        return repository.allMessages();
    }

    public Message getOneMessageById(Integer id){
        //return repository.oneMessagebyMessageId(id);
        return repository.getById(id);
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
