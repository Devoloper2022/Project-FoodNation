package com.example.project1.Facade.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private Long id;
    private Long foodID;
    private String foodName;
    private Integer price;
    private Long psc;
    private Long orderDetailId;
}
