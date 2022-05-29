package com.example.project1.Controllers;


import com.example.project1.CustomTemplate.Payload.response.MessageResponse;
import com.example.project1.CustomTemplate.Validations.ResponseErrorValidation;
import com.example.project1.Domain.OrderDetails;
import com.example.project1.Facade.OrderFacade;
import com.example.project1.Services.OrderService;
import com.example.project1.dto.FoodDTO;
import com.example.project1.dto.OrderDTO;
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
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderFacade orderFacade;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;


    @PostMapping("/add")
    public ResponseEntity<Object> addFood(@Valid @RequestBody OrderDTO orderDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        System.out.println("Sonik "+orderDTO);
        orderService.create(orderDTO, principal);
        return ResponseEntity.ok(new MessageResponse("Order created successfully"));
    }


    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getListFood(Principal principal) {
        List<OrderDTO> orderDTO=orderService.getAllOrders(principal)
                .stream()
                .map(orderFacade::orderToOrderDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(orderDTO,HttpStatus.OK);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getFoodDetails(@PathVariable("orderId") String orderId) {
        OrderDetails order = orderService.getByID(Long.parseLong(orderId));
        OrderDTO orderDTO = orderFacade.orderToOrderDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }
}
