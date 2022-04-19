package com.example.project1.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class LocalOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @OneToOne
    @JoinColumn(name = "managerID")
    private User manager;

    @Column
    private Integer rate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "generalOrganizationID")
    private GeneralOrganization generalOrganization;


    @OneToMany(mappedBy = "localOrganization")
    private List<User> userList;

    @OneToMany(mappedBy = "localOrganization")
    private List<Comment> commentList;

    public LocalOrganization() {
    }
}
