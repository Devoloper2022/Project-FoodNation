package com.example.project1.Repository;


import com.example.project1.Domain.Foods;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodsRepository extends CrudRepository<Foods,Long> {

    Optional<Foods> findFoodsByTitle(String title);
    Optional<Foods> findFoodsById(Long id);

}
