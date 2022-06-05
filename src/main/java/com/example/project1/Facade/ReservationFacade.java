package com.example.project1.Facade;

import com.example.project1.Domain.Reservation;
import com.example.project1.dto.ReservationDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ReservationFacade {

    public ReservationDTO objectToDto(Reservation reservation){
        ReservationDTO dto=new ReservationDTO();

        dto.setId(reservation.getId());
        dto.setDate(reservation.getDate());
        dto.setOrgID(reservation.getLocalOrganization().getId());
        dto.setPersons(reservation.getCustomer().getId());
        dto.setUserID(reservation.getCustomer().getId());
        dto.setPersons((long) reservation.getPersons());

        return  dto;
    }
}
