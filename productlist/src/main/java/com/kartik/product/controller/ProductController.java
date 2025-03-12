package com.kartik.product.controller;

import com.kartik.product.dto.CategoryDTO;
import com.kartik.product.dto.ProductDTO;
import com.kartik.product.service.CategoryService;
import com.kartik.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name="Product Rest API Crud Operation",
        description="Create, Read Update and Delete for Product REST API"
)
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    //Create Product
    @Operation(
            summary = "Create product",
            description = "REST API to create product"
    )
    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO){
        System.out.println("under Post Mapping, product Controller");
        ProductDTO productDTOResponse = productService.createProductDTO(productDTO);
        return new ResponseEntity<>(productDTOResponse, HttpStatus.CREATED);
    }

    //getAllProduct
    @Operation(
            summary = "Fetch all products",
            description = "REST API to fetch all products"
    )
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        System.out.println("under Get Mapping, Products Controller");
        return productService.getAllProducts();

    }
    //get Product by ID
    @Operation(
            summary = "Fetch product by Id",
            description = "REST API to fetch product by Id"
    )
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        System.out.println("under Get Mapping by ID, Category Controller");
        return productService.getProductById(id);
    }

    //delete Product
    @Operation(
            summary = "Delete product by Id",
            description = "REST API to delete product by Id"
    )
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

    //update Product
    @Operation(
            summary = "Update product by Id",
            description = "REST API to update product by Id"
    )
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id, productDTO);
    }

}
