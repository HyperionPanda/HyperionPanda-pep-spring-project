package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Integer>{

    //refference
    // could also use @Query("From Account WHERE name = :variable")
    
    /* 
    @Query("From Account")
    List<Account> example(@Param("variable") String name);
    */


    //might need select for getting new account after insert
    @Query("INSERT INTO Account VALUES (:username,:password)")
    Account userRegistration(@Param("username") String user, @Param("password") String password);

    //
    @Query("FROM Account WHERE username = :user AND passwordd = :pass")
    Account userLogin(@Param("user") String username, @Param("pass") String password);

}
