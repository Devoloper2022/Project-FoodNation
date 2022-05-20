package com.example.project1.Domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class GeneralOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;


    @OneToOne
    @JoinColumn(name = "managerID", nullable = false)
    private User manager;

    @OneToMany(mappedBy = "generalOrganization")
    private List<LocalOrganization> localOrganizationList;

    @OneToMany(mappedBy = "organization")
    private List<Food> foodList;

    @OneToMany(mappedBy = "generalOrganization")
    private List<User> userList;

    public GeneralOrganization() {
    }

}
