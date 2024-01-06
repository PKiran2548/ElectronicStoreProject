package com.example.curd.opperation.serviceImpl;

import com.example.curd.opperation.controller.UserController;
import com.example.curd.opperation.dto.ProductDto;
import com.example.curd.opperation.entity.Category;
import com.example.curd.opperation.entity.Product;
import com.example.curd.opperation.exception.ResourceNotFoundException;
import com.example.curd.opperation.helper.Helper;
import com.example.curd.opperation.helper.PagebleResponse;
import com.example.curd.opperation.repository.CategoryRepo;
import com.example.curd.opperation.repository.ProductRepo;
import com.example.curd.opperation.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo ;

    @Autowired
    private ModelMapper modelMapper;

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        log.info("Initiating request to create product ");
        Product product = modelMapper.map(productDto, Product.class);
        // creating ID using UUID
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        product.setAddedDate(new Date());
        Product product1 = this.productRepo.save(product);
        ProductDto productDto1 = modelMapper.map(product1, ProductDto.class);
        log.info("Product created sucessfully");
        return productDto1;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String productId) {
        log.info("Initiating request to update the product");
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException(" Resource Not Found With The Given Id"));
        product.setTitle(productDto.getTitle());
        product.setLive(productDto.isLive());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setStock(productDto.isStock());
        product.setDescription(productDto.getDescription());
        product.setProductImageName(productDto.getProductImageName());

        Product updatedProduct = this.productRepo.save(product);

        ProductDto updatedProductDto1 = modelMapper.map(updatedProduct, ProductDto.class);
        log.info("product updated sucessfully");

        return updatedProductDto1;
    }

    @Override
    public void deleteProduct(String productId) {
        log.info("Initiating the request to delete the product");
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException(" Resource Not Found With The Given Id"));
        log.info("Product deleted sucessfully");
        this.productRepo.delete(product);

    }

    @Override
    public ProductDto getSingleProduct(String productId) {
        log.info("Initiating the request to get the single product");
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Of This Id Not Found"));
        log.info("Single product fetched sucessfully");
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    @Override
    public PagebleResponse<ProductDto> getAllProduct(Integer pageNumber , Integer pageSize , String sortBy , String sortDir) {
        log.info("Initiating the request to get list of all product");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber , pageSize , sort);
        Page<Product> page = this.productRepo.findAll(pageable);
        log.info("List of all products fetched sucessfully");
        return Helper.getPagebleResponse(page ,ProductDto.class);
    }

    @Override
    public PagebleResponse<ProductDto> getAllLiveProduct(Integer pageNumber , Integer pageSize , String sortBy , String sortDir) {
        log.info("Initiating the request to get list of all live product");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber , pageSize , sort);
        Page<Product> page = this.productRepo.findByLiveTrue(pageable);
        log.info("List of all live products fetched sucessfully");
        return Helper.getPagebleResponse(page ,ProductDto.class);

    }

    @Override
    public  PagebleResponse<ProductDto> searchByTitle(String title , Integer pageNumber , Integer pageSize , String sortBy , String sortDir) {
        log.info("Initiating the request to get product by title");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber , pageSize , sort);
        Page<Product> page = this.productRepo.findByTitleContaining(title ,pageable);
        log.info("Product fetch tile request completed sucessfully");
        return Helper.getPagebleResponse(page ,ProductDto.class);
    }
}
