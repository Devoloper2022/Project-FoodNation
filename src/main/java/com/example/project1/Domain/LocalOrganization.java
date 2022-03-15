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
    @JoinColumn(name = "managerID", nullable = false)
    private Staff manager;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "generalOrganizationID", nullable = false)
    private GeneralOrganization generalOrganization;

    @OneToMany(mappedBy = "localOrganization")
    private List<Staff> staffList;

    public LocalOrganization() {
    }
}
