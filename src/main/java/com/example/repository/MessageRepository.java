package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer>{


    //refference
    // could also use @Query("From Account WHERE name = :variable")
    
    /* 
    @Query("From Message")
    List<Message> example(@Param("variable") String name);
    */

    //instead of String account might need type Account
    /* 
    @Modifying
    @Query("INSERT INTO Message VALUES (:postedBy,:text,:timePosted)")
    Message newMessage(@Param("postedBy") Integer account,@Param("text") String text,@Param("timePosted") Long posted_time);
    */
/*   
    @Query("FROM Message")
    List<Message> allMessages();

    @Query("FROM Message WHERE message_id = :id")
    Message oneMessagebyMessageId(@Param("id") Integer message_id);

    //needs to return number of rows updated
    @Modifying
    @Query("DELETE FROM Message WHERE message_id = :id")
    Integer deleteMessagebyId(@Param("id") Integer message_id);
*/
    //issue may happen with set, needs to return number of rows updated
    @Transactional
    @Modifying
    @Query("UPDATE Message SET message_text = :text WHERE message_id = :messageId")
    Integer updateMessagebyId(@Param("messageId") Integer message_id, @Param("text") String text);

    //
    @Query("FROM Message WHERE posted_by = :accountId  ")
    List<Message> allMessagesbyUser(@Param("accountId") Integer account_id );

    

}
