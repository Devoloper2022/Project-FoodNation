package com.example.project1.Repository;

import com.example.project1.Domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findCustomerByUsername(String username);

    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findCustomerById(Long id);

}
