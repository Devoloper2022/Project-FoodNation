package com.example.project1.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Foods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "text")
    private String description;
    @Column(length = 5)
    private Long price;

    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] imageByte;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
    private List<Orders> orderList = new ArrayList<>();

    public Foods() {
    }
}
