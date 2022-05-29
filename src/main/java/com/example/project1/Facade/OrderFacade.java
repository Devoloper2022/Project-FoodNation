package com.example.project1.Facade;

import com.example.project1.Domain.Additional.CartItem;
import com.example.project1.Domain.OrderDetails;
import com.example.project1.Domain.OrdersDetails_food;
import com.example.project1.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class OrderFacade {

    public OrderDTO  orderToOrderDTO(OrderDetails order){
        OrderDTO orderDTO=new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setUserID(order.getCustomer().getId());
        orderDTO.setAddress(order.getAddress());
//        orderDTO.setCart(convertToCard(order.getOrderList()));

        orderDTO.setLocalDateTime(order.getLocalDateTime());
        orderDTO.setTotalCost(order.getTotalCost());
        orderDTO.setOrgID(order.getGenOrganization().getId());

        return  orderDTO;
    }


//    private List<CartItem> convertToCard(Map foods) {
//        List<CartItem> list =new ArrayList<>();
//        for (OrdersDetails_food i : foods) {
//            CartItem cartItem=new CartItem();
//            cartItem.setItemId(i.getId());
//            cartItem.setAmountItem(i.getPcs());
//        }
//
//        return list;
//    }
}
