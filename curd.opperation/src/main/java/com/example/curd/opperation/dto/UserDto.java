package com.example.curd.opperation.dto;

import com.example.curd.opperation.validate.ImageNameValid;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId ;

    @NotEmpty()
    @Pattern(regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"
            , message = "username is 8-20 characters long")
    private String userName;

    @NotEmpty
   @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"
                        , message = "Invalid Password")
    private String password;


    @Pattern(regexp = "/[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*/.")
    @Email(message = "Enter Valied email")
    private String email;

    @NotEmpty
    @Size(min=4 ,max = 6 , message = "Invalid Gender")
    private String gender;

    @NotEmpty
    @Size(min = 15 , max = 1000 , message = " Tail about your self")
    private String about ;

    // we use custom validator to validate imageNmae

    @ImageNameValid
    private String imageName;



    /*
    REGULER EXPRESSION OF USER_NAME

    ^(?=.{8,20}$) ----> username is 8-20 characters long
    (?![_.]) ---------> no _ or . at the beginning
    (?!.*[_.]{2}) ----->  no __ or _. or ._ or .. inside
    [a-zA-Z0-9._] ------> allowed characters
    (?<![_.])$ ------->  no _ or . at the end

     REGULER EXPRESSION OF PASSWORD

     At least one upper case English letter, (?=.*?[A-Z])
    At least one lower case English letter, (?=.*?[a-z])
    At least one digit, (?=.*?[0-9])
    At least one special character, (?=.*?[#?!@$%^&*-])
    Minimum eight in length .{8,} (with the anchors)



     */
}
