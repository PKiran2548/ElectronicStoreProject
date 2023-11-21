package com.example.curd.opperation.helper;

import com.example.curd.opperation.dto.UserDto;
import com.example.curd.opperation.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {


    public static <U ,V > PagebleResponse<V> getPagebleResponse (Page<U> page,Class<V> type){

        List<U> userList = page.getContent(); // return list of pages
        List<V> userDtos = userList.stream().map((user) -> new ModelMapper().map(user,type)).collect(Collectors.toList());

        PagebleResponse<V> response = new PagebleResponse<>();
        response.setContaint(userDtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElement(page.getTotalElements());
        response.setLastPage(page.isLast());

        return response ;
    }
}
