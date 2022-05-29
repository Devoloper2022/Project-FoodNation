package com.example.project1.Services;


import com.example.project1.Domain.*;
import com.example.project1.Domain.Additional.CartItem;
import com.example.project1.Repository.*;
import com.example.project1.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class OrderService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    private final UserRepository userRepository;
    private final OrderDetailsRepository orderDetAilsRepository;
    private final OrdersRepository ordersRepository;
    private final GeneralOrganizationRepository genRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public OrderService(UserRepository userRepository, OrderDetailsRepository orderDetAilsRepository, OrdersRepository ordersRepository, GeneralOrganizationRepository genRepository, FoodRepository foodRepository) {
        this.userRepository = userRepository;
        this.orderDetAilsRepository = orderDetAilsRepository;
        this.ordersRepository = ordersRepository;
        this.genRepository = genRepository;
        this.foodRepository = foodRepository;
    }

    public OrderDetails create(OrderDTO orderDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        GeneralOrganization organization = genRepository.findById(orderDTO.getOrgID()).get();

        OrderDetails details = new OrderDetails();
        details.setAddress(orderDTO.getAddress());
        details.setCustomer(user);
        details.setGenOrganization(organization);
        details.setTotalCost(orderDTO.getTotalCost());

        details = orderDetAilsRepository.save(details);

//        details.setOrderList(createCard(details.getId(), orderDTO.getCart()));
        createCard(details.getId(), orderDTO.getCart());

        LOG.info("Order {} ", user.getEmail() + " " + details.getId());
        return orderDetAilsRepository.save(details);
    }

//    public OrderDetails update(OrderDTO orderDTO,Principal principal){
//
//    }

    public  OrderDetails getByID(Long id){
        return orderDetAilsRepository.findById(id).get();
    }

    public List<OrderDetails> getAllOrders(Principal principal){
        User user=getUserByPrincipal(principal);
        return  orderDetAilsRepository.findByCustomerOrderByLocalDateTimeDesc(user);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));
    }

//    private List<OrdersDetails_food> createCard(Long id, List<CartItem> cart) {
//        List<OrdersDetails_food> list = new ArrayList<>();
//
//        OrderDetails details = orderDetAilsRepository.findById(id).get();
//
//        for (CartItem i : cart) {
//            OrdersDetails_food orders = new OrdersDetails_food();
//            Food food = foodRepository.findFoodById(i.getItemId()).get();
//            orders.setFood(food);
//            orders.setPcs(i.getAmountItem());
//            orders.setOrderDetails(details);
//            orders = ordersRepository.save(orders);
//
//            list.add(orders);
//
//        }
//        return list;
//    }

    private void createCard(Long id, Map<String,String> cart) {
        OrderDetails details = orderDetAilsRepository.findById(id).get();
        System.out.println("Sonik "+cart);
        Set set=cart.entrySet();
        Iterator<CartItem> i = set.iterator();
        while (i.hasNext()) {
            Map.Entry entry=(Map.Entry)i.next();
            OrdersDetails_food orders = new OrdersDetails_food();
            Food food = foodRepository.findFoodById(Long.parseLong((String) entry.getKey())).get();
            orders.setFood(food);
            orders.setPcs((Integer) entry.getValue());
            orders.setOrderDetails(details);
            ordersRepository.save(orders);
        }
//        for (CartItem i : cart) {
//            OrdersDetails_food orders = new OrdersDetails_food();
//            Food food = foodRepository.findFoodById(i.getItemId()).get();
//            orders.setFood(food);
//            orders.setPcs(i.getAmountItem());
//            orders.setOrderDetails(details);
//            orders = ordersRepository.save(orders);
//        }

    }


}
