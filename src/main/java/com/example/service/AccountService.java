package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository newRepository){
        repository = newRepository;
    }

    
    public Account registerUser(Account account){
       
        if(!(account.getUsername().isEmpty()) && (account.getPassword()).length() >= 4 && repository.existsByUsername(account.getUsername())== null){
            return repository.save(account);
        }else{
            return null;
        }
    }

    
    public Account loginAccount(Account account){
        
        Account testAccount = repository.userLogin(account.getUsername(),account.getPassword());
    
        return testAccount;
    }

}
