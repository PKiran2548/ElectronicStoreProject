package com.example.curd.opperation.service;

import com.example.curd.opperation.dto.UserDto;
import com.example.curd.opperation.helper.PagebleResponse;

import java.util.List;

public interface UserService {

     UserDto createUser (UserDto userDto);

     UserDto getSingleUser (String userId);



     UserDto updateUser (UserDto userDto , String userId);

     boolean deleteUser (String userId);

     UserDto getUserByUserName (String userNmae);

     UserDto getUserByEmail (String email);

    UserDto getUserByEmailAndPassword (String email ,String password);

     List<UserDto> searchUser (String keyword);


    PagebleResponse<UserDto> getAllUser(int pageNumber, int pageSize);
}
