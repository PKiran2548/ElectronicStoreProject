package com.example.curd.opperation.service;

import com.example.curd.opperation.dto.ProductDto;
import com.example.curd.opperation.helper.PagebleResponse;

import java.util.List;

public interface ProductService {

    // create

    ProductDto createProduct (ProductDto productDto);

    // update

    ProductDto updateProduct (ProductDto productDto , String productId);

    //delete

    void deleteProduct (String productId);

    // get single

    ProductDto getSingleProduct (String productId);

    // get all

    PagebleResponse<ProductDto> getAllProduct (Integer pageNumber , Integer pageSize , String sortBy , String sortDir );

    // get all ; live

    PagebleResponse<ProductDto> getAllLiveProduct (Integer pageNumber , Integer pageSize , String sortBy , String sortDir);

    // search product

    PagebleResponse<ProductDto> searchByTitle (String title , Integer pageNumber , Integer pageSize , String sortBy , String sortDir);

    // other methods

}
