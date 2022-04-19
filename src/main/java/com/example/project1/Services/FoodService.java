package com.example.project1.Services;

import com.example.project1.Domain.Dictionary.DFoodType;
import com.example.project1.Domain.Food;
import com.example.project1.Domain.GeneralOrganization;
import com.example.project1.Domain.User;
import com.example.project1.Repository.FoodTypeRepository;
import com.example.project1.Repository.FoodsRepository;
import com.example.project1.Repository.UserRepository;
import com.example.project1.dto.FoodDTO;
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
    private final FoodsRepository foodRepository;
    private final UserRepository userRepository;

    @Autowired
    public FoodService(FoodTypeRepository foodTypeRepository, FoodsRepository foodRepository, UserRepository userRepository) {
        this.foodTypeRepository = foodTypeRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
    }


    public Food createFood(FoodDTO foodDTO, Principal principal){
        GeneralOrganization organization =getUserByPrincipal(principal).getLocalOrganization().getGeneralOrganization();
        Food food=new Food();
        food.setTitle(foodDTO.getTitle());
        food.setDescription(foodDTO.getDescription());
        food.setPrice(foodDTO.getPrice());
        food.setRate(0);
        food.getFoodTypes().addAll(convertLongToFoodType(foodDTO));
        food.setOrganization(organization);

        LOG.info("Create food {}", organization.getId());
        return foodRepository.save(food);
    }

    public Food getFoodById(Long id){
        return foodRepository.findFoodById(id).get();
    }

    public List<Food> getMenu(Long id){
        return foodRepository.findFoodByOrganization(id);
    }

    private Set<DFoodType> convertLongToFoodType(FoodDTO dto){
        Set<DFoodType> types=new HashSet<>();
        Iterator<Long> i=dto.getListType().iterator();
        while(i.hasNext())
        {
            types.add(foodTypeRepository.findById(i.next()).get());
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
