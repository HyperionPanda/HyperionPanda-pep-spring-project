package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Component
public class AccountService {

    AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository newRepository){
        repository = newRepository;
    }

    //needs to check if account already exists in database, use login once finished?
    public Account registerUser(Account account){

        if(!(account.getUsername().isEmpty()) && (account.getPassword()).length() >= 4){
            return repository.userRegistration(account.getUsername(),account.getPassword());
        }else{
            return null;
        }
        
    }

    //does this need account id?
    //should do a check? or check if return is null?
    public Account loginAccount(Account account){
        return repository.userLogin(account.getUsername(),account.getPassword());
    }

}
