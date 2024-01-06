package com.example.curd.opperation.controller;

import com.example.curd.opperation.dto.ApiResponse;
import com.example.curd.opperation.dto.CreateOrderRequest;
import com.example.curd.opperation.dto.OrderDto;
import com.example.curd.opperation.helper.PagebleResponse;
import com.example.curd.opperation.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    Logger log = LoggerFactory.getLogger(OrderController.class);


    /**
     * @author kiran paithane
     * @apiNote to create order
     * @since 4.0.0
     * @param request
     * @return
     */
    @PostMapping("/creat")
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        log.info("Getting request to create order");
        OrderDto order = orderService.createOrder(request);
        log.info("Create order request completed");
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    /**
     * @author kiran paithane
     * @apiNote to remove order
     * @since 4.0.0
     * @param orderId
     * @return
     */

    @DeleteMapping("/remove/{orderId}")
    public ResponseEntity<ApiResponse> removeOrder(@PathVariable String orderId) {
        log.info("Getting request to remove order");
        orderService.removeOrder(orderId);
        ApiResponse responseMessage = ApiResponse.builder()
                .status(HttpStatus.OK)
                .massage("order is removed !!")
                .sucess(true)
                .build();
        log.info("Remove order request completed");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    //get orders of the user

    /**
     * @author kiran paithane
     * @apiNote to get the order of the user
     * @since 4.0.0
     * @param userId
     * @return
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersOfUser(@PathVariable String userId) {
        log.info("Getting request to get the order of user");
        List<OrderDto> ordersOfUser = orderService.getOrdersOfUser(userId);
        log.info("Get order of user request completed");
        return new ResponseEntity<>(ordersOfUser, HttpStatus.OK);
    }

    /**
     * @author kiran paithane
     * @apiNote to get all orders
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    @GetMapping
    public ResponseEntity<PagebleResponse<OrderDto>> getAllOrders(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "orderedDate", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir
    ) {
        log.info("Getting request to fetch list of all orders");
        PagebleResponse<OrderDto> orders = orderService.getOrders(pageNumber, pageSize, sortBy, sortDir);
        log.info("Getting list of all orders request completed ");
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


}



