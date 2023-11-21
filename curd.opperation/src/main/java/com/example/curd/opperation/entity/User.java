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
    private String userId ;


    private String userName;

    private String password;

    private String email;

    private String gender;

    private String about ;

    private String imageName ;
}
