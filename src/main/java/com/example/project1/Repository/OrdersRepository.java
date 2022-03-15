package com.example.project1.Repository;

import com.example.project1.Domain.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends CrudRepository<Orders,Long> {
    Optional<Orders> findOrdersById(Long id);
}
