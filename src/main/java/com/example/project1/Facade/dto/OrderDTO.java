package com.example.project1.Facade.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;


@Data
public class OrderDTO {
    private Long id;
    private String address;
    private Long userID;
    private Long orgID;
    private int totalCost;
    private LocalDateTime localDateTime;
   private Map<Long,Long> Cart;
}
