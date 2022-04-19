package com.example.project1.dto;

import com.example.project1.Domain.Additional.CartItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class OrderDTO {
    private Long id;
    private String address;
    private Long userID;
    private Long orgID;
    private LocalDateTime localDateTime;
   private List<CartItem> Cart;
}
