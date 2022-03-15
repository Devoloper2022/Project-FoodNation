package com.example.project1.Domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuID", nullable = false)
    private Foods menu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderDetailsID", nullable = false)
    private OrderDetails orderDetails;

    public Orders() {
    }
}
