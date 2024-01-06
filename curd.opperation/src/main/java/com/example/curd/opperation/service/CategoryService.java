package com.example.curd.opperation.service;

import com.example.curd.opperation.dto.CategoryDto;
import com.example.curd.opperation.helper.PagebleResponse;

public interface CategoryService {

    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //delete

    void delete(String categoryId);

    //get all
    PagebleResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single category detail
    CategoryDto get (String categoryId);


}
