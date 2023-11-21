package com.example.curd.opperation.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor

@Builder
public class ApiResponse {

    private String massage ;
    private boolean sucess ;
    private HttpStatus status ;

    public ApiResponse(String massage, boolean sucess, HttpStatus status) {
        this.massage = massage;
        this.sucess = sucess;
        this.status = status;
    }
}
