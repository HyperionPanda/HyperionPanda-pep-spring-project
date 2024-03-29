package com.example.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.exception.BadMessageRequest;
import com.example.exception.NoMessageExists;
import com.example.exception.UnauthorizedUser;
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
        Account returnedAccount = accountService.registerUser(account);
        if (returnedAccount != null){
            return returnedAccount;
        }else{
            throw new UserExists();
        } 
    }
    
    @PostMapping(value = "/login")
    public Account loginUser(@RequestBody Account account){
        Account returnedAccount = accountService.loginAccount(account);
        if(returnedAccount == null){
            throw new UnauthorizedUser();
        }else{
            return returnedAccount;
        }
    }

    @GetMapping(value = "/accounts/{account_id}/messages")
    public List<Message> getMessagesFromUser(@PathVariable("account_id") int id){
        List<Message> messageList = messageService.getAllMsgsByUser(id);
         return messageList;
    }

    @PostMapping(value = "/messages")
    public Message createMessage(@RequestBody Message message){
        Message retunedMessage = messageService.createMessage(message);
        if( retunedMessage == null){
                throw new BadMessageRequest();
        }else{
            return retunedMessage;
        }
    }

    @GetMapping(value = "/messages")
    public List<Message> getAllMessages(){

        List<Message> retunedMessages = messageService.getAllMessages();

        return retunedMessages;
    }

    @GetMapping(value = "/messages/{message_id}")
    public Message getOneMessageGivenMessageID(@PathVariable("message_id") int id){
        Message retunedMessage = messageService.getOneMessageById(id);
        
        return retunedMessage;
    }

    @DeleteMapping(value = "/messages/{message_id}")
    public int deleteMessageGivenMessageID(@PathVariable("message_id") int id){
        int retunedRowsAffected = messageService.deleteMsgById(id);
        return retunedRowsAffected;

    }


    @PatchMapping(value = "/messages/{message_id}")
    public int updateMessageGivenMessageID(@PathVariable("message_id") int id, @RequestBody String message_text){
        int retunedRowsAffected = messageService.updateMsgById(id,message_text);

        if(retunedRowsAffected <= 0){
            throw new NoMessageExists();
        }else{
            return retunedRowsAffected;
        }
        
    }

    @ExceptionHandler(UserExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody void handleUserFound(UserExists ex) {}

    @ExceptionHandler(UnauthorizedUser.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody void handleUnauthorizeUser(UnauthorizedUser ex) {}

    @ExceptionHandler(BadMessageRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody void handleBadMessageRequest(BadMessageRequest ex) {}

    @ExceptionHandler(NoMessageExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody void handleNoMessageExists(NoMessageExists ex) {}

    
    


}
