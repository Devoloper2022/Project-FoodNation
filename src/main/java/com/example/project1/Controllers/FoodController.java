package com.example.project1.Controllers;


import com.example.project1.CustomTemplate.Payload.response.MessageResponse;
import com.example.project1.CustomTemplate.Validations.ResponseErrorValidation;
import com.example.project1.Domain.Food;
import com.example.project1.Facade.FoodFacade;
import com.example.project1.Services.FoodService;
import com.example.project1.dto.FoodDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodFacade foodFacade;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;


    @GetMapping("/{foodId}")
    public ResponseEntity<FoodDTO> getFoodDetails(@PathVariable("foodId") String foodId) {
        Food food = foodService.getFoodById(Long.parseLong(foodId));
        FoodDTO foodDTO = foodFacade.foodToFoodDTO(food);
        return new ResponseEntity<>(foodDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addFood(@Valid @RequestBody FoodDTO foodDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        foodService.createFood(foodDTO, principal);
        return ResponseEntity.ok(new MessageResponse("Staff added successfully"));
    }



}
