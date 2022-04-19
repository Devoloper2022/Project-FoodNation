package com.example.project1.Domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrdersDetails_food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuID", nullable = false)
    private Food menu;
    private Integer amountOfMenu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderDetailsID", nullable = false)
    private OrderDetails orderDetails;

    public OrdersDetails_food() {
    }
}
