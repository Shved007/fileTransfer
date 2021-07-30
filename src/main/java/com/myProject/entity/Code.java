package com.myProject.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "code")
@Data
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "verification_code",nullable = false,unique = true)
    private String verificationCode;

    @OneToOne
    @JoinColumn(name = "message_id",nullable = false)
    private Message message;



}
