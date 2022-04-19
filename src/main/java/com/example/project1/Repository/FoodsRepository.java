package com.example.project1.Repository;


import com.example.project1.Domain.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodsRepository extends CrudRepository<Food, Long> {
    Optional<Food> findFoodByTitle(String title);

    Optional<Food> findFoodById(Long id);

    List<Food> findAllOrdOrderByPrice(Long price);

    List<Food> findFoodByOrganization(Long Id);

}
