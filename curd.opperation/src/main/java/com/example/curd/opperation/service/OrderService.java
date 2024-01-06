package com.example.curd.opperation.service;

import com.example.curd.opperation.dto.CreateOrderRequest;
import com.example.curd.opperation.dto.OrderDto;
import com.example.curd.opperation.helper.PagebleResponse;

import java.util.List;

public interface OrderService {

    //create order
    OrderDto createOrder(CreateOrderRequest orderDto);

    //remove order
    void removeOrder(String orderId);

    //get orders of user
    List<OrderDto> getOrdersOfUser(String userId);

    //get orders
    PagebleResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir);
}
