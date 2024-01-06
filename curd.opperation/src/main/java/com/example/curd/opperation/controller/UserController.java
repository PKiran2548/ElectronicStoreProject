package com.example.curd.opperation.controller;

import com.example.curd.opperation.dto.ApiResponse;
import com.example.curd.opperation.dto.ImageResponse;
import com.example.curd.opperation.dto.UserDto;
import com.example.curd.opperation.helper.AppConstant;
import com.example.curd.opperation.helper.PagebleResponse;
import com.example.curd.opperation.service.ImageService;
import com.example.curd.opperation.service.UserService;
import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private ImageService fileService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath; // we declare path in application.properties file


    Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * @param userDto
     * @return Dto
     * @author Kiran Paithane
     * @apiNote create user in database
     * @since 4.0.0
     */

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Getting the request to create the user ");
        UserDto Dto = this.userService.createUser(userDto);
        log.info("Create User Request Completed");
        return new ResponseEntity<>(Dto, HttpStatus.CREATED);
    }

    /**
     * @param userId
     * @return singleUser
     * @author Kiran Paithane
     * @apiNote get single user from database using userId
     * @since 4.0.0
     */
    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable String userId) {
        log.info("Getting the request to get single User");
        UserDto singleUser = this.userService.getSingleUser(userId);
        log.info("To get Single User Request Completed");
        return new ResponseEntity<>(singleUser, HttpStatus.OK);
    }

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortby
     * @return listOfUsers
     * @author Kiran Paithane
     * @apiNote getting all user with page size and page Number
     * @since 4.0.0
     */
    @GetMapping("/getAll")
    public ResponseEntity<PagebleResponse<UserDto>> getAllUser(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortby", defaultValue = AppConstant.SORT_DIR, required = false) String sortby

    ) {
        log.info("Getting The Request to get All users");
        PagebleResponse<UserDto> allUser = this.userService.getAllUser(pageNumber, pageSize);
        log.info("To get All Users Request Completed");
        return new ResponseEntity<>(allUser, HttpStatus.OK);

    }

    /**
     * @param userDto
     * @param userId
     * @return updataedUserDto
     * @author Kiran Paithane
     * @apiNote update the user in database
     * @since 4.0.0
     */
    @PutMapping("/update/{user}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId) {
        log.info("Getting the request to update the user");
        UserDto updateUserDto = this.userService.updateUser(userDto, userId);
        log.info("Update User request completed");
        return new ResponseEntity<>(updateUserDto, HttpStatus.CREATED);
    }

    /**
     * @param userId
     * @return user delete confurmation message
     * @author Kiran Paithane
     * @apiNote detete the user from datatbse
     * @since 4.0.0
     */
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) throws IOException {
        log.info("Getting request to delete the user");
        boolean result = this.userService.deleteUser(userId);
        ApiResponse massage = ApiResponse.builder()
                .massage("User Deleted SucessFully")
                .sucess(true)
                .status(HttpStatus.OK)
                .build();
        log.info("Delete user request completed ");
        return new ResponseEntity<>(massage, HttpStatus.OK);
    }

    /**
     * @param userName
     * @return userByUserName
     * @author Kiran Paithnae
     * @apiNote getting user using user name
     * @since 4.0.0
     */

    @GetMapping("/byNmae/{userName}")
    public ResponseEntity<UserDto> userByUserName(@PathVariable String userName) {
        log.info("Getting request to get User detail Using user name");
        UserDto userByUserName = this.userService.getUserByUserName(userName);
        log.info("Getting user detail using user name completed");
        return new ResponseEntity<>(userByUserName, HttpStatus.FOUND);
    }

    /**
     * @param email
     * @param password
     * @return userByEmailAndPassword
     * @author Kiran Paithane
     * @apiNote getting user by using Email and Password
     * @since 4.0.0
     */
    @GetMapping("email/{email}/password/{password}")
    public ResponseEntity<UserDto> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        log.info("Getting request to find user using Email and Password");
        UserDto userByEmailAndPassword = this.userService.getUserByEmailAndPassword(email, password);
        log.info("To find user using Email and Password is completed");
        return new ResponseEntity<>(userByEmailAndPassword, HttpStatus.OK);
    }

    /**
     * @param keyword
     * @return userDtos
     * @author Kiran Paithane
     * @apiNote searche user using keyword
     * @since 4.0.0
     */
    @GetMapping("search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword) {
        log.info("Getting request to search user using keyword");
        List<UserDto> userDtos = this.userService.searchUser(keyword);
        log.info("search user using keyword is completed");
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    // upload user image

    @PostMapping("/userImage/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage") MultipartFile image, @PathVariable String userId) throws IOException, IOException {
        log.info("Getting request to upload the user image");

        String imageName = fileService.uploadImage(image, imageUploadPath);
        UserDto user = userService.getSingleUser(userId);

        user.setImageName(imageName);
        UserDto userDto = userService.updateUser(user, userId);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).sucess(true).massage("image is uploaded successfully ").status(HttpStatus.CREATED).build();

        log.info("user image uploded sucessfully");
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

    }

    // serve user image

    @GetMapping(value = "/image/{userId}")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        UserDto user = userService.getSingleUser(userId);
        log.info("User image name : {} ", user.getImageName());
        InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}
