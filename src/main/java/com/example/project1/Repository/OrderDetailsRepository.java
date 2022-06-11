package com.example.project1.Repository;


import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.OrderDetails;
import com.example.project1.Domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
    List<OrderDetails> findByCustomerOrderByLocalDateTimeDesc(User user);
    List<OrderDetails> findAllByGenOrganization(GeneralOrganization organization);
}
