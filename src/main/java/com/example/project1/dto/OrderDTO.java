package com.example.project1.dto;

import com.example.project1.Domain.Additional.CartItem;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Data
public class OrderDTO {
    private Long id;
    private String address;
    private Long userID;
    private Long orgID;
    private int totalCost;
    private LocalDateTime localDateTime;
   private Map Cart;
}
