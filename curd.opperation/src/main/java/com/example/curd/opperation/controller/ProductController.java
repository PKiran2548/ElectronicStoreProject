package com.example.curd.opperation.controller;

import com.example.curd.opperation.dto.ApiResponse;
import com.example.curd.opperation.dto.ImageResponse;
import com.example.curd.opperation.dto.ProductDto;
import com.example.curd.opperation.dto.UserDto;
import com.example.curd.opperation.helper.PagebleResponse;
import com.example.curd.opperation.service.ImageService;
import com.example.curd.opperation.service.ProductService;
import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService ;

    @Autowired
    private ImageService imageService ;

    @Value("${product.image.path}")
    private String imagePath;

    Logger log = LoggerFactory.getLogger(UserController.class);

    // create

    /**
     * @Authour Kiran Paithane
     * @apiNote to create product
     * @since 4.0.0
     * @param productDto
     * @return
     */
    @PostMapping("/creat")
    public ResponseEntity<ProductDto> createProduct (@RequestBody ProductDto productDto){
        log.info("Getting request to create product");
        ProductDto product = this.productService.createProduct(productDto);
        log.info("Create product request completed ");
        return new ResponseEntity<>(product , HttpStatus.CREATED);
    }

    // update

    /**
     * @Author kiran Paithane
     * @apiNote to update the product
     * @since 4.0.0
     * @param productDto
     * @param productId
     * @return
     */
    @PostMapping("/Update/{productId}")
    public ResponseEntity<ProductDto> updateProduct (@RequestBody ProductDto productDto ,@PathVariable String productId){
        log.info("Getting request to update product");
        ProductDto updateProductDto = this.productService.updateProduct(productDto, productId);
        log.info("Update Product request completed");
        return new ResponseEntity<>(updateProductDto , HttpStatus.OK);
    }

    // delete

    /**
     * @Authour kiran paithane
     * @apiNote to delete the product
     * @param productId
     * @return
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct (@PathVariable String productId){
        log.info("Getting request to delete the product");
        this.productService.deleteProduct(productId);
        ApiResponse apiResponse = ApiResponse.builder().massage("Product Deleted SucessFully").sucess(true).status(HttpStatus.OK).build();
        log.info("Delete Product request completed");
        return new ResponseEntity<>(apiResponse , HttpStatus.OK);
    }
    // get single

    /**
     * @author kiran paithane
     * @apiNote to get single product
     * @since 4.0.0
     * @param productId
     * @return
     */
    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct (@PathVariable String productId){
        log.info("Getting rquest to get single product");
        ProductDto singleProduct = this.productService.getSingleProduct(productId);
        log.info("Get single user request completed sucessfully");
        return new ResponseEntity<>(singleProduct ,HttpStatus.FOUND);
    }

    // get all

    /**
     * @author kiran paithane
     * @apiNote to get the list of products
     * @since 4.0.0
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    @GetMapping("/getAll")
    public ResponseEntity<PagebleResponse<ProductDto>> getAllProduct (
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        log.info("Getting request to get list of all product");
        PagebleResponse<ProductDto> allProduct = this.productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);
        log.info("Getting list of product request completed sucessfully");
        return new ResponseEntity<>(allProduct , HttpStatus.OK);
    }

    // get all Live

    /**
     * @author kiran paithane
     * @apiNote to get the list of all live product
     * @since 4.0.0
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */

    @GetMapping("/getAll/live")
    public ResponseEntity<PagebleResponse<ProductDto>> getAllLiveProduct (
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        log.info("Getting Request to get list of all live product");
        PagebleResponse<ProductDto> allProduct = this.productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);
        log.info("Getting list of all live products completed");
        return new ResponseEntity<>(allProduct , HttpStatus.OK);
    }

    // search all

    /**
     * @author kiran paithane
     * @apiNote to get product searched by keyword
     * @since 4.0.0
     * @param title
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    @GetMapping("/search/{title}")
    public ResponseEntity<PagebleResponse<ProductDto>> searchProduct (
            @PathVariable String title,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        log.info("Getting request to search product");
        PagebleResponse<ProductDto> allProduct = this.productService.searchByTitle(title ,pageNumber, pageSize, sortBy, sortDir);
        log.info("Getting searched product request completed sucessfully");
        return new ResponseEntity<>(allProduct , HttpStatus.OK);
    }


    // upload Image

    /**
     * @author kiran paithane
     * @apiNote to upload the product image
     * @since 4.0.0
     * @param productId
     * @param image
     * @return
     * @throws IOException
     */
    @PostMapping("/image/{productId}")
    public ResponseEntity<ImageResponse> uploadProductImage (
            @PathVariable String productId ,
            @RequestParam ("productImage") MultipartFile image
    ) throws IOException {
        String imageName = imageService.uploadImage(image, imagePath);
        ProductDto productDto = productService.getSingleProduct(productId);
        productDto.setProductImageName(imageName);
        ProductDto updatedProduct = productService.updateProduct(productDto, productId);

        ImageResponse imageResponse = ImageResponse.builder()
                .imageName(updatedProduct.getProductImageName())
                .massage("Product Image Uploaded Sucessfully")
                .sucess(true)
                .status(HttpStatus.CREATED)
                .build();

        return new ResponseEntity<>(imageResponse , HttpStatus.CREATED);
    }
    // serve image

    /**
     * @author kiran paithane
     * @apiNote to serve image
     * @since 4.0.0
     * @param productId
     * @param response
     * @throws IOException
     */
    @GetMapping(value = "/image/{productId}")
    public void serveUserImage(@PathVariable String productId, HttpServletResponse response) throws IOException {
        ProductDto productDto = this.productService.getSingleProduct(productId);

        InputStream resource = imageService.getResource(imagePath,productDto.getProductImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}




