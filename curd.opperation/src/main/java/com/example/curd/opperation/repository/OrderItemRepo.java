package com.example.curd.opperation.repository;

import com.example.curd.opperation.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo  extends JpaRepository<OrderItem, Integer> {
}
