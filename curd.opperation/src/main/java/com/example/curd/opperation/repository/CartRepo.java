package com.example.curd.opperation.repository;

import com.example.curd.opperation.entity.Cart;
import com.example.curd.opperation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CartRepo extends JpaRepository<Cart, String> {


    Optional<Cart> findByUser(User user);

}
