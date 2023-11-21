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

    private int pageNumber ;

    private int pageSize;

    private int totalPages ;

    private long totalElement ;

    private boolean lastPage ;

}
