package com.myProject.repository;

import com.myProject.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Long> {

    @Query("select m from Message m join Code c on m.id = c.message.id where c.verificationCode = ?1")
    Optional<Message> findByCode(String code);

    List<Message> findByIsReviewedIsTrue();
}
