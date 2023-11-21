package com.example.curd.opperation.serviceImpl;

import com.example.curd.opperation.dto.UserDto;
import com.example.curd.opperation.entity.User;
import com.example.curd.opperation.exception.ResourceNotFoundException;
import com.example.curd.opperation.helper.Helper;
import com.example.curd.opperation.helper.PagebleResponse;
import com.example.curd.opperation.repository.UserRepo;
import com.example.curd.opperation.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.image.RasterFormatException;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo ;

    @Autowired
    private ModelMapper modelMapper ;

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Initiating request to save user in database");
        String id = UUID.randomUUID().toString();
        userDto.setUserId(id);
        User user = this.modelMapper.map(userDto, User.class);

        User saveUser = this.userRepo.save(user);
        UserDto userDto1 = this.modelMapper.map(saveUser, UserDto.class);

        log.info("User saved in database sucessfully");

        return userDto1;
    }

    @Override
    public UserDto getSingleUser(String userId) {
        log.info("Initiating the request to get single user");
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        log.info("user fetch from database suessfully");
        return userDto;
    }



    @Override
    public PagebleResponse<UserDto> getAllUser(int pageNumber , int pageSize) {
        log.info("Initiating the request to fetch all user from database");
        /*
        pageble is an interface ---- so we create object using impl class PageRequest
         */
        Pageable pageble = PageRequest.of(pageNumber, pageSize); // return pagble object

        Page<User> page = this.userRepo.findAll(pageble); // return single page
        PagebleResponse<UserDto> response = Helper.getPagebleResponse(page, UserDto.class);

        log.info("All users are fetch from database sucessfully ");
        return response;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        log.info("Initiating the request to update the user");

        User user = new User();

        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());
        user.setImageName(userDto.getImageName());
        user.setImageName(userDto.getImageName());

        UserDto updatedUser = this.modelMapper.map(user, UserDto.class);
        log.info("User updated sucessfully ");

        return updatedUser;
    }

    @Override
    public boolean deleteUser(String userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
        if (user != null){
            this.userRepo.delete(user);
            return true ;
        }else{
            return false ;
        }
    }

    @Override
    public UserDto getUserByUserName(String userNmae) {
        UserDto byUserName = this.userRepo.findByUserName(userNmae);
        return byUserName;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserDto byEmail = this.userRepo.findByEmail(email);
        return byEmail;
    }

    @Override
    public UserDto getUserByEmailAndPassword(String email, String password) {
        UserDto byEmailAndPassword = this.userRepo.findByEmailAndPassword(email, password);
        return byEmailAndPassword;
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> nameContaining = this.userRepo.findByUserNameContaining(keyword);
        List<UserDto> userDtoList = nameContaining.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtoList;
    }
}
