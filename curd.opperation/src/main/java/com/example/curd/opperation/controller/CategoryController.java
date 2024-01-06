package com.example.curd.opperation.controller;

import com.example.curd.opperation.dto.ApiResponse;
import com.example.curd.opperation.dto.CategoryDto;
import com.example.curd.opperation.dto.ProductDto;
import com.example.curd.opperation.helper.PagebleResponse;
import com.example.curd.opperation.service.CategoryService;
import com.example.curd.opperation.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/catrgories")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * @Author kiran paithane
     * @apiNote to create category
     * @Since 4.0.0
     * @param categoryDto
     * @return
     */
    //create
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("getting rwquest to create category");
        CategoryDto categoryDto1 = categoryService.create(categoryDto);
        log.info("category created sucessfully");
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    /**
     * @Author kiran paithane
     * @apiNote api to update the category
     * @param categoryId
     * @param categoryDto
     * @return
     */

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable String categoryId,
            @RequestBody CategoryDto categoryDto
    ) {
        log.info("getting request to update category");
        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        log.info("category upated sucessfully ");
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    /**
     * @Author kiran paithane
     * @apiNote to delete the category
     * @param categoryId
     * @return
     */
    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(
            @PathVariable String categoryId
    ) {
        log.info("getting request to delete category");
        categoryService.delete(categoryId);
        ApiResponse responseMessage = ApiResponse.builder().massage("Category is deleted successfully").status(HttpStatus.OK).sucess(true).build();
        log.info("category deleted sucessfully");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);


    }

    /**
     * @Authour kiran paithane
     * @apiNote to get list of category with page number and page size
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */

    //get all

    @GetMapping("/getAll")
    public ResponseEntity<PagebleResponse<CategoryDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir


    ) {
        log.info("getting request to get list of all user");
        PagebleResponse<CategoryDto> pageableResponse = categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);
        log.info("sucessfully get the list of user");
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }

    /**
     * @author kiran paithane
     * @apiNote to get the single category
     * @param categoryId
     * @return
     */

    //get single
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingle(@PathVariable String categoryId) {
        log.info("getting request to get single category");
        CategoryDto categoryDto = categoryService.get(categoryId);
        log.info("sucessfuly get single category");
        return ResponseEntity.ok(categoryDto);
    }




}


