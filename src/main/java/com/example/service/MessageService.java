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

    
    public Message createMessage(Message message){
        if (message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && userRepository.existsById(message.getPosted_by())){
            return repository.save(message);
        }else{
            return null;
        }
    }

    public List<Message> getAllMessages(){
        return repository.findAll();
    }

    public Message getOneMessageById(Integer id){
        return repository.oneMessagebyMessageId(id);
       
    }

    public Integer deleteMsgById(Integer id){
        return repository.deleteMessagebyId(id);
    }

    public Integer updateMsgById(Integer id, String message_text){

        //message_text includes json label of message_text, making the default length 20 not 0
        int textLength = message_text.length()-20;

        if (textLength > 0 && textLength < 255 && repository.existsById(id) ){
            return repository.updateMessagebyId(id, message_text);
        }else{
            return 0;

        }
    }
    

    public List<Message> getAllMsgsByUser(Integer user_id){
        
        return repository.allMessagesbyUser(user_id);
    }

}
