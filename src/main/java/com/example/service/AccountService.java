package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;
import com.example.exception.HandleException;
import com.example.exception.UserExists;

@Service
public class AccountService {

    AccountRepository repository;

    /*
    System.out.println(account.getUsername());
        System.out.println(account.getPassword());
        System.out.println(testAccount); */

    @Autowired
    public AccountService(AccountRepository newRepository){
        repository = newRepository;
    }

    //needs to check if account already exists in database, use login once finished?
    //MIGHT NEED TO USE SAVE!!!!!
    
    public Account registerUser(Account account){
       
        if(!(account.getUsername().isEmpty()) && (account.getPassword()).length() >= 4){
            
            return repository.save(account);
        }else{
            return null;
        }
    }

    //does this need account id?
    //should do a check? or check if return is null?
    public Account loginAccount(Account account){
        
        Account testAccount = repository.userLogin(account.getUsername(),account.getPassword());
    
        return testAccount;
    }

}
