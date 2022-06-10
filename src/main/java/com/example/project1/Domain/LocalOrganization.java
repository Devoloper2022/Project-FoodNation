package com.example.project1.Domain;

import com.example.project1.Domain.Dictionary.DOrganizationType;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(columnDefinition = "text")
    private String urlImage;

    @ManyToOne
    private DOrganizationType category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "generalOrganizationID")
    private GeneralOrganization generalOrganization;


    @OneToMany(mappedBy = "localOrganization")
    private List<User> StaffList;


    @OneToMany(mappedBy = "localOrganization")
    private List<Comment> commentList;

//    @OneToMany(mappedBy = "localOrganization")
//    private List<OrderDetails> orderList;

    @OneToMany(mappedBy = "localOrganization")
    private List<Reservation> reservationList;

    @ElementCollection(targetClass = String.class)
    private Set<String> checkTime=new HashSet<>();

    // for rating
    @Column
    private Double rate;
    @Column
    private Integer counter;

    @ElementCollection(targetClass = String.class)
    private Set<String> ratedUser=new HashSet<>();

    @Column
    private  boolean delete;

    public LocalOrganization() {
    }

}
