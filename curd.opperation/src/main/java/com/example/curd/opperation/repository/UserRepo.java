package com.example.curd.opperation.repository;

import com.example.curd.opperation.dto.UserDto;
import com.example.curd.opperation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository <User, String> {

     UserDto findByUserName(String userName);

     UserDto findByEmail (String email);

     UserDto findByEmailAndPassword (String email , String password);

     List<User> findByUserNameContaining (String keyword);
}
