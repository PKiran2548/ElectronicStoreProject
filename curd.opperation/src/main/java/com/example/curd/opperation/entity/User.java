package com.example.curd.opperation.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name="user_id")
    private String userId ;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_password")
    private String password;

    @Column(name="user_email")
    private String email;

    @Column(name="user_gender")
    private String gender;

    @Column(name="about_user")
    private String about ;

    @Column(name="user_image")
    private String imageName ;
}
