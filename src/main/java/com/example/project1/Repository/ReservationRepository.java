package com.example.project1.Repository;

import com.example.project1.Domain.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation,Long> {
}
