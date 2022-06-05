package com.example.project1.dto;

import lombok.Data;

@Data
public class ReservationDTO {
    private Long id;
    private String date;
    private Long persons;
    private Long orgID;
    private Long orderID;
    private Long userID;
}
