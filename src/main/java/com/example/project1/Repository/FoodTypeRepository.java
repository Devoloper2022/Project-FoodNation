package com.example.project1.Repository;

import com.example.project1.Domain.Dictionary.DFoodType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTypeRepository extends CrudRepository<DFoodType,Long>{
}
