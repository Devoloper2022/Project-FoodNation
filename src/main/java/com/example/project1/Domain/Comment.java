package com.example.project1.Domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private Integer rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", nullable = false)
    private User customer;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localOrganizationID")
    private LocalOrganization localOrganization;

    @Column
    private  boolean delete;


    @PrePersist
    protected void onCreated(){
        this.createdDate =LocalDateTime.now();
    }

}
