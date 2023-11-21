package com.example.curd.opperation.controller;

import com.example.curd.opperation.dto.ApiResponse;
import com.example.curd.opperation.dto.UserDto;
import com.example.curd.opperation.helper.AppConstant;
import com.example.curd.opperation.helper.PagebleResponse;
import com.example.curd.opperation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService ;

    Logger log = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser (@Valid @RequestBody UserDto userDto){
        log.info("Getting the request to create the user ");
        UserDto Dto = this.userService.createUser(userDto);
        log.info("Create User Request Completed");
    return new ResponseEntity<>(Dto , HttpStatus.CREATED);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDto> getSingleUser ( @PathVariable String userId){
        log.info("Getting the request to get single User");
        UserDto singleUser = this.userService.getSingleUser(userId);
        log.info("To get Single User Request Completed");
        return new ResponseEntity<>(singleUser , HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<PagebleResponse<UserDto>> getAllUser (
            @RequestParam(value = "pageNumber" ,defaultValue = AppConstant.PAGE_NUMBER,required = false) int pageNumber ,
            @RequestParam(value = "pageSize" ,defaultValue = AppConstant.PAGE_SIZE ,required = false) int pageSize ,
            @RequestParam(value = "sortBy" , defaultValue = AppConstant.SORT_BY ,required = false) String sortBy ,
            @RequestParam(value = "sortby", defaultValue = AppConstant.SORT_DIR , required = false) String sortby

    )
    {
        log.info("Getting The Request to get All users");
        PagebleResponse<UserDto> allUser = this.userService.getAllUser(pageNumber ,pageSize );
        log.info("To get All Users Request Completed");
        return new ResponseEntity<>(allUser , HttpStatus.OK);

    }
    @PutMapping("/update/{user}")
    public ResponseEntity<UserDto> updateUser (@Valid @RequestBody UserDto userDto , @PathVariable String userId){
       log.info("Getting the request to update the user");
        UserDto updateUserDto = this.userService.updateUser(userDto, userId);
        log.info("Update User request completed");
        return new ResponseEntity<>(updateUserDto , HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser (@PathVariable String userId) {
        log.info("Getting request to delete the user");
        boolean result = this.userService.deleteUser(userId);
            ApiResponse massage = ApiResponse.builder()
                    .massage("User Deleted SucessFully")
                    .sucess(true)
                    .status(HttpStatus.OK)
                    .build();
      log.info("Delete user request completed ");
            return new ResponseEntity<>(massage , HttpStatus.OK);
    }

    @GetMapping("/byNmae/{userName}")
    public ResponseEntity<UserDto> userByUserName (@PathVariable String userName){
        log.info("Getting request to get User detail Using user name");
        UserDto userByUserName = this.userService.getUserByUserName(userName);
        log.info("Getting user detail using user name completed");
        return new ResponseEntity<>(userByUserName , HttpStatus.FOUND);
    }

    @GetMapping("email/{email}/password/{password}")
    public ResponseEntity<UserDto> getUserByEmailAndPassword (@PathVariable String email ,@PathVariable String password){
        log.info("Getting request to find user using Email and Password");
        UserDto userByEmailAndPassword = this.userService.getUserByEmailAndPassword(email, password);
        log.info("To find user using Email and Password is completed");
        return new ResponseEntity<>(userByEmailAndPassword , HttpStatus.OK);
    }
    @GetMapping("search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser (@PathVariable String keyword){
        log.info("Getting request to search user using keyword");
        List<UserDto> userDtos = this.userService.searchUser(keyword);
        log.info("search user using keyword is completed");
        return new ResponseEntity<>(userDtos , HttpStatus.OK);
    }
}