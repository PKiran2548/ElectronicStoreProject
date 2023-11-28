package com.example.curd.opperation.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponse {

    private String imageName ;
    private String massage ;
    private boolean sucess ;
    private HttpStatus status ;
}
