package com.example.project1.Domain;

import com.example.project1.Domain.Dictionary.DFoodType;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "text")
    private String description;
    @Column
    private Integer price;

    @ManyToMany
    private Set<DFoodType> foodTypes =new HashSet<>();

    @Column(length = 2)
    private Integer rate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
    private List<OrdersDetails_food> orderList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "generalOrganizationID", nullable = false)
    private GeneralOrganization organization;

    public Food() {
    }
}
