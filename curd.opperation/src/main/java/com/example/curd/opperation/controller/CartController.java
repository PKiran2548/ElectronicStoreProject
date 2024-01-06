package com.example.curd.opperation.controller;

import com.example.curd.opperation.dto.AddItemToCartRequest;
import com.example.curd.opperation.dto.ApiResponse;
import com.example.curd.opperation.dto.CartDto;
import com.example.curd.opperation.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    Logger log = LoggerFactory.getLogger(CartController.class);

    /**
     * @author kiran paithane
     * @apiNote Api to add item in the cart
     * @since 4.0.0
     * @param userId
     * @param request
     * @return
     */

    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable String userId, @RequestBody AddItemToCartRequest request) {
        log.info("Getting request to add item in cart");
        CartDto cartDto = cartService.addItemToCart(userId, request);
        log.info("Request for Add item to cart completed");
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    /**
     * @author kiran paithane
     * @apiNote Api to remove item from cart
     * @since 4.0.0
     * @param userId
     * @param itemId
     * @return
     */

    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable String userId, @PathVariable int itemId) {
        log.info("Getting Request to remove item from cart");
        cartService.removeItemFromCart(userId, itemId);
        ApiResponse response = ApiResponse.builder()
                .massage("Item is removed !!")
                .sucess(true)
                .status(HttpStatus.OK)
                .build();
        log.info("Request for Remove item from cart is completed");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /**
     * @author kiran paithane
     * @apiNote Api to cler the cart
     * @since 4.0.0
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable String userId) {
       log.info("Getting request to clear cart");
        cartService.clearCart(userId);
        ApiResponse response = ApiResponse.builder()
                .massage("Now cart is blank !!")
                .sucess(true)
                .status(HttpStatus.OK)
                .build();
        log.info("Request to clear cart is completed");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /**
     * @author kiran paithane
     * @apiNote Api to get all item from cart
     * @since 4.0.0
     * @param userId
     * @return
     */
    //add items to cart
    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId) {
        log.info("Getting request to get all items from cart");
        CartDto cartDto = cartService.getCartByUser(userId);
        log.info("Request to get all items from cart is completed");
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }



}
