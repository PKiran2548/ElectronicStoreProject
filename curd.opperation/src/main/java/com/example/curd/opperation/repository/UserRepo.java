package com.example.curd.opperation.repository;

import com.example.curd.opperation.dto.UserDto;
import com.example.curd.opperation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <User, String> {

     UserDto findByUserName(String userName);

     Optional<User> findByEmail (String email);

     UserDto findByEmailAndPassword (String email , String password);

     List<User> findByUserNameContaining (String keyword);
}
