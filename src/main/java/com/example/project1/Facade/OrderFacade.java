package com.example.project1.Facade;

import com.example.project1.Domain.OrderDetails;
import com.example.project1.Domain.OrdersDetails_food;
import com.example.project1.Facade.dto.ItemDTO;
import com.example.project1.Facade.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class OrderFacade {

    public OrderDTO ordersToOrderDTO(OrderDetails order) {
        OrderDTO orderDTO = new OrderDTO();


        orderDTO.setId(order.getId());
        orderDTO.setUserID(order.getCustomer().getId());
        orderDTO.setAddress(order.getAddress());
//        orderDTO.setMenuItems(convert(order.getOrderList()));
        orderDTO.setLocalDateTime(order.getLocalDateTime());
        orderDTO.setTotalCost(order.getTotalCost());
        orderDTO.setOrgID(order.getGenOrganization().getId());

        return orderDTO;
    }


    public ItemDTO orderDet_FoodToItemDTO(OrdersDetails_food order){
        ItemDTO itemDTO =new ItemDTO();
        itemDTO.setId(order.getId());
        itemDTO.setFoodID(order.getFood().getId());
        itemDTO.setFoodName(order.getFood().getTitle());
        itemDTO.setPrice(order.getFood().getPrice());
        itemDTO.setPsc(order.getPcs());
        itemDTO.setOrderDetailId(order.getOrderDetails().getId());

        return  itemDTO;
    }

    public OrderDTO orderToOrderDTO(OrderDetails order) {
        OrderDTO orderDTO = new OrderDTO();


        orderDTO.setId(order.getId());
        orderDTO.setUserID(order.getCustomer().getId());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setMenuItems(convert(order.getOrderList()));
        orderDTO.setLocalDateTime(order.getLocalDateTime());
        orderDTO.setTotalCost(order.getTotalCost());
        orderDTO.setOrgID(order.getGenOrganization().getId());

        return orderDTO;
    }

    private Map<Long, Long> convert(List<OrdersDetails_food> list) {
        Map<Long, Long> cart = new HashMap<Long, Long>();

        Iterator<OrdersDetails_food> i = list.iterator();
//        while (i.hasNext()) {
//            System.out.println("FSonik");
//            cart.put(
//                    i.next().getFood().getId(),
//                    i.next().getPcs()
//            );
//        }

//        for (Map.Entry<String, String> set :
//                foodTable.entrySet()) {
//
//            // Printing all elements of a Map
//            System.out.println(set.getKey() + " = "
//                    + set.getValue());
//        }


        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println("SOnik"+String.valueOf(i.next().getFood().getId()));
//            System.out.println("SOnik"+String.valueOf(i.next().getPcs()));
//            cart.put(
//                    i.next().getFood().getId(),
//                    i.next().getPcs()
//            );
        }
        return cart;
    }



}


