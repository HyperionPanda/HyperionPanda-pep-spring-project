package com.example.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import com.example.exception.HandleException;
import com.example.exception.UserExists;

import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    AccountService accountService;

    @Autowired 
    MessageService messageService;

    
    @PostMapping(value = "register")
    public Account registerUser(@RequestBody Account account){
        try{
            Account returnedAccount = accountService.registerUser(account);
        
            return returnedAccount;
        }catch(Exception e){
            throw new UserExists();
        } 
    }
    @ExceptionHandler(UserExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody void handleUserFound(UserExists ex) {}
    
/* 
    @PostMapping(value = "/login")
    public Account loginUser(@RequestBody Account account){
        Account returnedAccount = accountService.loginAccount(account);
        if(returnedAccount == null){
            //response status 400
        }else{
            //response status 200, not needed to specify?
            //return account?
        }
        return null;

    }

    @GetMapping(value = "/accounts/{account_id}/messages")
    public List<Message> getMessagesFromUser(@PathVariable("account_id") int id){
        List<Message> messageList = messageService.getAllMsgsByUser(id);
        if(messageList.isEmpty()){
            //response status 400
        }else{
            //response status 200, not needed to specify?
            //return account?
        }
        return null;

    }

    @PostMapping(value = "/messages")
    public Message createMessage(@RequestBody Message message){
        Message retunedMessage = messageService.createMessage(message);
        if(retunedMessage == null){
            //response status 400
        }else{
            //response status 200, not needed to specify?
            //return account?
        }
        return null;

    }

    @GetMapping(value = "/messages")
    public List<Message> getAllMessages(){
        List<Message> retunedMessage = messageService.getAllMessages();
        if(retunedMessage == null){
            //response status 400
        }else{
            //response status 200, not needed to specify?
            //return account?
        }
        return null;

    }

    @GetMapping(value = "/messages/{message_id}")
    public Message getOneMessageGivenMessageID(@PathVariable("message_id") int id){
        Message retunedMessage = messageService.getOneMessageById(id);
        
        return retunedMessage;

    }

    @DeleteMapping(value = "/messages/{message_id}")
    public int deleteMessageGivenMessageID(@PathVariable("message_id") int id){
        int retunedRowsAffected = messageService.deleteMsgById(id);
        if(retunedRowsAffected <= 0){
            //response status 400
        }else{
            //response status 200, not needed to specify?
            //return account?
        }
        return -1;

    }

    /* 
    @PatchMapping(value = "/messages/{message_id}")
    public int updateMessageGivenMessageID(@PathVariable("message_id") int id){
        int retunedRowsAffected = messageService.
        if(retunedRowsAffected <= 0){
            //response status 400
        }else{
            //response status 200, not needed to specify?
            //return account?
        }
        return -1;

    }
    */


}
