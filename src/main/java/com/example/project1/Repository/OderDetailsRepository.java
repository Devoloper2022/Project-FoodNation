package com.example.project1.Repository;

import com.example.project1.Domain.Customer;
import com.example.project1.Domain.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OderDetailsRepository extends CrudRepository<OrderDetails, Long> {

    List<OrderDetails> findAllByCustomerOrderByLocalDateTimeDesc(Customer customer);

//    Optional<OrderDetails> findByIdAndCustomer(Long id,Customer customer);
}
