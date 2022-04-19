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
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    private final UserRepository userRepository;
    private final OrderDetailsRepository orderDetAilsRepository;
    private final OrdersRepository ordersRepository;
    private final LocalOrganizationRepository organizationRepository;
    private final FoodsRepository foodsRepository;

    @Autowired
    public OrderService(UserRepository userRepository, OrderDetailsRepository orderDetAilsRepository, OrdersRepository ordersRepository, LocalOrganizationRepository organizationRepository, FoodsRepository foodsRepository) {
        this.userRepository = userRepository;
        this.orderDetAilsRepository = orderDetAilsRepository;
        this.ordersRepository = ordersRepository;
        this.organizationRepository = organizationRepository;
        this.foodsRepository = foodsRepository;
    }

    public OrderDetails create(OrderDTO orderDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        LocalOrganization organization = organizationRepository.findLocalOrganizationById(orderDTO.getId()).get();

        OrderDetails details = new OrderDetails();
        details.setAddress(orderDTO.getAddress());
        details.setCustomer(user);
        details.setLocalOrganization(organization);

        details = orderDetAilsRepository.save(details);

        details.setOrderList(createCard(details.getId(), orderDTO.getCart()));

        LOG.info("Order {} ", user.getEmail() + " " + details.getId());
        return orderDetAilsRepository.save(details);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));
    }

    private List<OrdersDetails_food> createCard(Long id, List<CartItem> cart) {
        List<OrdersDetails_food> list = new ArrayList<>();
        OrderDetails details = orderDetAilsRepository.findById(id).get();
        for (CartItem i : cart) {
            OrdersDetails_food orders = new OrdersDetails_food();
            Food food = foodsRepository.findFoodById(i.getItemId()).get();
            orders.setMenu(food);
            orders.setAmountOfMenu(i.getAmountItem());
            orders.setOrderDetails(details);
            orders = ordersRepository.save(orders);

            list.add(orders);

        }
        return list;
    }
}
