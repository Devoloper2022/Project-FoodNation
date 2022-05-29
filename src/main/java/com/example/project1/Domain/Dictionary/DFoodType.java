package com.example.project1.Domain.Dictionary;

import com.example.project1.Domain.Food;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class DFoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(columnDefinition = "text")
    private String urlImage;
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "foodTypes")
    private List<Food> foods;

}
