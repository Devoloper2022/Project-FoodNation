package com.example.project1.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
public class FoodDTO {
    private Long id;
    @NotEmpty
    private String title;
    private String description;
    private Integer price;
    private Integer rate;
    private String urlImage;
    private Set<Long> listType =new HashSet<>();
}
