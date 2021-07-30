package com.myProject.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_to_send",nullable = false)
    private String messageToSend;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(name = "reviewed",nullable = false)
    private boolean isReviewed = false;
}
