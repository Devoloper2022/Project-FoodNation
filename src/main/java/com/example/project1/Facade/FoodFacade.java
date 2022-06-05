package com.example.project1.Facade;

import com.example.project1.Domain.Dictionary.DFoodType;
import com.example.project1.Domain.Food;
import com.example.project1.dto.FoodDTO;
import com.example.project1.dto.FoodTypeDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class FoodFacade {
    public FoodDTO foodToFoodDTO(Food food){
        FoodDTO foodDTO= new FoodDTO();
        foodDTO.setId(food.getId());
        foodDTO.setTitle(food.getTitle());
        foodDTO.setDescription(food.getDescription());
        foodDTO.setPrice(food.getPrice());
        foodDTO.setListType(food.getFoodTypes().getId());
        foodDTO.setUrlImage(food.getUrlImage());
        foodDTO.setRate(food.getRate());

//        foodDTO.setListType(convertFoodTypeToLong(food.getFoodTypes()));
        return foodDTO;
    }

    public FoodTypeDTO foodTypeToFoodTypeDTO(DFoodType foodType){
        FoodTypeDTO typeDTO = new FoodTypeDTO();
        typeDTO.setId(foodType.getId());
        typeDTO.setName(foodType.getName());
        typeDTO.setUrlImage(foodType.getUrlImage());

        return   typeDTO;
    }


    private Set<Long> convertFoodTypeToLong(Set<DFoodType> list) {
        Set<Long> types = new HashSet<>();

        Iterator<DFoodType> i = list.iterator();
        while (i.hasNext()) {
            types.add(i.next().getId());
        }

        return types;
    }
}
