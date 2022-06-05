package com.example.project1.Services;

import com.example.project1.Domain.Dictionary.DFoodType;
import com.example.project1.Domain.Food;
import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.User;
import com.example.project1.Repository.FoodTypeRepository;
import com.example.project1.Repository.FoodRepository;
import com.example.project1.Repository.GeneralOrganizationRepository;
import com.example.project1.Repository.UserRepository;
import com.example.project1.dto.FoodDTO;
import com.example.project1.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class FoodService {
    public static final Logger LOG = LoggerFactory.getLogger(FoodService.class);


    private final FoodTypeRepository foodTypeRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final GeneralOrganizationRepository GORepository;

    @Autowired
    public FoodService(FoodTypeRepository foodTypeRepository, FoodRepository foodRepository, UserRepository userRepository, GeneralOrganizationRepository goRepository) {
        this.foodTypeRepository = foodTypeRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
        GORepository = goRepository;
    }


    public Food createFood(FoodDTO foodDTO, Principal principal) {
        GeneralOrganization organization = getUserByPrincipal(principal).getLocalOrganization().getGeneralOrganization();
       DFoodType foodType=foodTypeRepository.findById(foodDTO.getListType()).get();

        Food food = new Food();
        food.setTitle(foodDTO.getTitle());
        food.setDescription(foodDTO.getDescription());
        food.setPrice(foodDTO.getPrice());
        food.setRate(0);
//        food.setFoodTypes(convertLongToFoodType(foodDTO.getListType()));
        food.setFoodTypes(foodType);
        food.setOrganization(organization);
        food.setUrlImage(foodDTO.getUrlImage());

        LOG.info("Create food {}", organization.getId());
        return foodRepository.save(food);
    }

    public Food getFoodById(Long id) {
        return foodRepository.findFoodById(id).get();
    }

    public List<Food> getAll() {
        return (List<Food>) foodRepository.findAll();
    }

    public Food updateFood(FoodDTO foodDTO, Principal principal){
        GeneralOrganization genOrganization=getUserByPrincipal(principal).getGeneralOrganization();
        Food food= foodRepository.findById(foodDTO.getId()).get();
        DFoodType foodType=foodTypeRepository.findById(foodDTO.getId()).get();

        food .setDescription(foodDTO.getDescription());
        food.setOrganization(genOrganization);
        food.setPrice(foodDTO.getPrice());
        food.setTitle(foodDTO.getTitle());
        //        food.setFoodTypes(convertLongToFoodType(foodDTO.getListType()));
        food.setFoodTypes(foodType);
        food.setUrlImage(foodDTO.getUrlImage());

        return  foodRepository.save(food);
    }

    public List<Food> getMenuOrg(Long id) {
        GeneralOrganization organization = GORepository.findById(id).get();
//        return organization.getFoodList();
        return foodRepository.findFoodsByOrganization(organization);
    }

    public List<Food> getFoodsByType(Long id) {
    DFoodType foodType =foodTypeRepository.findById(id).get();
//        return organization.getFoodList();
        return foodRepository.findFoodByFoodTypes(foodType);
    }


    public List<DFoodType> getAllFoodType() {

        return (List<DFoodType>) foodTypeRepository.findAll();
    }


    public DFoodType getFoodTypeByID(Long id){
        return  foodTypeRepository.findById(id).get();
    }


    private Set<DFoodType> convertLongToFoodType(Set<Long> list) {
        Set<DFoodType> types = new HashSet<>();

        Iterator<Long> i = list.iterator();
        while (i.hasNext()) {
            DFoodType foodType = foodTypeRepository.findById(i.next().longValue()).get();
            types.add(foodType);
        }

        return types;
    }


    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));
    }
}
