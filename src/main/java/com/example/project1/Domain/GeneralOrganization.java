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

    @OneToOne
    @JoinColumn(name = "managerID", nullable = false)
    private Staff director;


    @OneToMany(mappedBy = "generalOrganization")
    private List<LocalOrganization> localOrganizationList;

    public GeneralOrganization() {
    }

}
