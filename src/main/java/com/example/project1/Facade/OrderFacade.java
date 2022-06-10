package com.example.project1.Facade;

import com.example.project1.Domain.OrderDetails;
import com.example.project1.Domain.OrdersDetails_food;
import com.example.project1.Facade.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class OrderFacade {

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
        Map<Long, Long> cart = new HashMap<>();

        Iterator<OrdersDetails_food> i = list.iterator();
        while (i.hasNext()) {
            cart.put(i.next().getFood().getId(), i.next().getPcs());
        }
        return cart;
    }




}


