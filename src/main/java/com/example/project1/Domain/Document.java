package com.example.project1.Domain;

import lombok.Data;

import javax.persistence.Column;

@Data
public class Document {
    private Long id;
    private String name;
    private String author;
    @Column
    private  Boolean delete;
}
