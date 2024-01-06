package com.example.curd.opperation.helper;

import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagebleResponse<T> {


    private List<T> containt ;

    private Integer pageNumber ;

    private Integer pageSize;

    private Integer totalPages ;

    private long totalElement ;

    private boolean lastPage ;

}
