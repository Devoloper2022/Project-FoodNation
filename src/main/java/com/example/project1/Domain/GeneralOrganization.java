package com.example.project1.Domain;


import com.example.project1.Domain.Dictionary.DOrganizationType;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(columnDefinition = "text")
    private String urlImage;

    @ElementCollection(targetClass = String.class)
    private Set<String> category=new HashSet<>();

    @ElementCollection(targetClass = String.class)
    private Set<String> foodType=new HashSet<>();

    @OneToOne
    @JoinColumn(name = "managerID", nullable = false)
    private User manager;

    @OneToMany(mappedBy = "generalOrganization")
    private List<LocalOrganization> filialBranchList;

    @OneToMany(mappedBy = "organization")
    private List<Food> menu;

    @OneToMany(mappedBy = "generalOrganization")
    private List<User> staffList;

    @OneToMany(mappedBy = "genOrganization")
    private List<OrderDetails> orderList;

    @Column
    private  Boolean delete;

    public GeneralOrganization() {
    }

}
