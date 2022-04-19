package com.example.project1.Repository;

import com.example.project1.Domain.OrdersDetails_food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends CrudRepository<OrdersDetails_food, Long> {
    Optional<OrdersDetails_food> findOrdersById(Long id);
}
