package com.example.curd.opperation.serviceImpl;

import com.example.curd.opperation.dto.CategoryDto;
import com.example.curd.opperation.entity.Category;
import com.example.curd.opperation.exception.ResourceNotFoundException;
import com.example.curd.opperation.helper.Helper;
import com.example.curd.opperation.helper.PagebleResponse;
import com.example.curd.opperation.repository.CategoryRepo;
import com.example.curd.opperation.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
     private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper ;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        String categoryId = UUID.randomUUID().toString();
        categoryDto.setCategoryId(categoryId);

        Category category = modelMapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepo.save(category);

        CategoryDto categoryDto1 = modelMapper.map(saveCategory, CategoryDto.class);

        return categoryDto1 ;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));

        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepo.save(category);

        CategoryDto categoryDto1 = modelMapper.map(updatedCategory, CategoryDto.class);
        return categoryDto1 ;
    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepo.delete(category);

    }

    @Override
    public PagebleResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> page = categoryRepo.findAll(pageable);
        PagebleResponse<CategoryDto> pageableResponse = Helper.getPagebleResponse(page, CategoryDto.class);
        return pageableResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {

            Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
            return modelMapper.map(category, CategoryDto.class);
    }
        }


