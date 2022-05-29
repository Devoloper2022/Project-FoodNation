package com.example.project1.Domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", nullable = false)
    private User customer;

    @Column
    private boolean Status;

    private int totalCost;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "localOrganizationID")
//    private LocalOrganization localOrganization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GenOrganizationID", nullable = false)
    private GeneralOrganization genOrganization;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime localDateTime;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "orderDetails")
    private List<OrdersDetails_food> orderList = new ArrayList<>();

    public OrderDetails() {
    }

    @PrePersist
    protected void onCreate(){
        this.localDateTime =LocalDateTime.now();
    }


}
