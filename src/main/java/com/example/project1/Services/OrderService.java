package com.example.project1.Services;


import com.example.project1.Domain.*;
import com.example.project1.Repository.*;
import com.example.project1.Facade.dto.OrderDTO;
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

        createCard(details.getId(), orderDTO.getMenuItems());

        LOG.info("Order {} ", user.getEmail() + " " + details.getId());
        return orderDetAilsRepository.save(details);
    }

    public OrderDetails changeStatus(Long id) {

        OrderDetails order = orderDetAilsRepository.findById(id).get();
        order.setStatus(true);
        return orderDetAilsRepository.save(order);
    }

    public OrderDetails getByID(Long id) {
        List<OrdersDetails_food> orders=Orders(id);
        OrderDetails orderDetails=orderDetAilsRepository.findById(id).get();
        orderDetails.setOrderList(orders);
        return  orderDetails;
    }

    public List<OrderDetails> getAllUserOrders(Principal principal){
        User user=getUserByPrincipal(principal);
        return  orderDetAilsRepository.findByCustomerOrderByLocalDateTimeDesc(user);
    }

   public List<OrdersDetails_food> Orders(Long id ){
        OrderDetails details = orderDetAilsRepository.findById(id).get();
        return ordersRepository.findOrdersDetails_foodByOrderDetails(details);
    }

    public List<OrderDetails> getAllGOOrders(Principal principal){
        User user=getUserByPrincipal(principal);
        GeneralOrganization organization=user.getGeneralOrganization();
        return  orderDetAilsRepository.findAllByGenOrganization(organization);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));
    }

    private void  createCard (Long id, Map<Long, Long> cart) {
        List<OrdersDetails_food> list = new ArrayList<>();

        OrderDetails details = orderDetAilsRepository.findById(id).get();

        Set set = cart.entrySet();
        Iterator<Map> i = set.iterator();

        while (i.hasNext()) {
            Map.Entry entry = (Map.Entry) i.next();
            OrdersDetails_food orders = new OrdersDetails_food();
            Food food = foodRepository.findFoodById((Long) entry.getKey()).get();
            orders.setFood(food);
            orders.setPcs((Long) entry.getValue());
            orders.setOrderDetails(details);
            ordersRepository.save(orders);
        }
    }



//    public List<OrderDetails> getAllUserOrders(Principal principal) {
//
//        User user = getUserByPrincipal(principal);
//        List<OrderDetails> menuItems = orderDetAilsRepository.findByCustomerOrderByLocalDateTimeDesc(user);
//        Iterator<OrderDetails> i = menuItems.iterator();
//
//
//        while (i.hasNext()) {
//            if (i.next().getOrderList().isEmpty()){
//                menuItems.remove(i.next());
//            }else {
//                i.next().setOrderList(
//                        convertID(
//                                i.next()
//                        )
//                );
//            }
//
//        }
//
//        return menuItems;
//    }
//    private List<OrdersDetails_food> convertID(OrderDetails details) {
//        return ordersRepository.findOrdersDetails_foodByOrderDetails(details);
//    }


}
