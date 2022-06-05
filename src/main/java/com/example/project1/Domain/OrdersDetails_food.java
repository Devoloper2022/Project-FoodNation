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
    @JoinColumn(name = "foodID", nullable = false)
    private Food food;
    private Long pcs; //amount of food
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderDetailsID", nullable = false)
    private OrderDetails orderDetails;

    public OrdersDetails_food() {
    }
}
