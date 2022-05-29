package com.example.project1.Repository;


import com.example.project1.Domain.Dictionary.DFoodType;
import com.example.project1.Domain.Food;
import com.example.project1.Domain.GeneralOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {
    Optional<Food> findFoodById(Long id);

    List<Food> findFoodsByOrganization(GeneralOrganization org);

    List<Food>findFoodByFoodTypes(DFoodType foodType);
}
