package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{

    /*  refference
    
    could also use @Query("From Account WHERE name = :variable")

    @Query("From Account")
    List<Account> example(@Param("variable") String name);
    
    */

    @Query("FROM Account WHERE username = :user AND password = :pass")
    Account userLogin(@Param("user") String username, @Param("pass") String password);

    @Query("FROM Account WHERE username = :user")
    Account existsByUsername(@Param("user") String user);
}
