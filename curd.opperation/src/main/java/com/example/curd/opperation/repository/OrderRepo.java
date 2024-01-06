package com.example.curd.opperation.repository;

import com.example.curd.opperation.entity.Order;
import com.example.curd.opperation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepo extends JpaRepository<Order, String> {

    List<Order> findByUser(User user);
}
