package com.example.project1.Controllers;


import com.example.project1.CustomTemplate.Payload.response.MessageResponse;
import com.example.project1.CustomTemplate.Validations.ResponseErrorValidation;
import com.example.project1.Domain.Dictionary.DFoodType;
import com.example.project1.Domain.Food;
import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Facade.FoodFacade;
import com.example.project1.Services.FoodService;
import com.example.project1.dto.FoodDTO;

import com.example.project1.dto.FoodTypeDTO;
import com.example.project1.dto.GOrganizationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
        return ResponseEntity.ok(new MessageResponse("Food added successfully"));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateFood(@Valid @RequestBody FoodDTO foodDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Food food= foodService.updateFood(foodDTO, principal);
        FoodDTO dto=foodFacade.foodToFoodDTO(food);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodDTO>> getListFood() {
        List<FoodDTO> foodDTOList=foodService.getAll()
                .stream()
                .map(foodFacade::foodToFoodDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(foodDTOList,HttpStatus.OK);
    }

    @GetMapping("/all/{orgId}")
    public ResponseEntity<List<FoodDTO>> getListFoodOrgId(@PathVariable("orgId") String orgId) {
        List<FoodDTO> foodDTOList=foodService.getMenuOrg(Long.parseLong(orgId))
                .stream()
                .map(foodFacade::foodToFoodDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(foodDTOList,HttpStatus.OK);
    }


    @GetMapping("/type")
    public ResponseEntity<List<FoodTypeDTO>> getListTypeId() {
        List<FoodTypeDTO> typeDTOList=foodService.getAllFoodType()
                .stream()
                .map(foodFacade::foodTypeToFoodTypeDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(typeDTOList,HttpStatus.OK);
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<FoodTypeDTO> getListTypeId(@PathVariable("id") String id) {
        DFoodType type = foodService.getFoodTypeByID(Long.parseLong(id));
        FoodTypeDTO typeDTO = foodFacade.foodTypeToFoodTypeDTO(type);
        return new ResponseEntity<>(typeDTO, HttpStatus.OK);
    }

}
