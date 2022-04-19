package com.example.project1.Facade;

import com.example.project1.Domain.Food;
import com.example.project1.dto.FoodDTO;
import org.springframework.stereotype.Component;

@Component
public class FoodFacade {
    public FoodDTO foodToFoodDTO(Food food){
        FoodDTO foodDTO= new FoodDTO();
        foodDTO.setId(food.getId());
        foodDTO.setTitle(food.getTitle());
        foodDTO.setDescription(food.getDescription());
        foodDTO.setPrice(food.getPrice());
        foodDTO.setListType(foodDTO.getListType());
        foodDTO.setRate(food.getRate());
        return foodDTO;
    }
}
