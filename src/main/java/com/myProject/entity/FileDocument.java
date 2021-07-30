package com.myProject.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "files")
public class FileDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "message_id",nullable = false)
    private Message message;

}
