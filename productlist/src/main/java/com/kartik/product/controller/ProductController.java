package com.kartik.product.controller;

import com.kartik.product.dto.CategoryDTO;
import com.kartik.product.dto.ProductDTO;
import com.kartik.product.service.CategoryService;
import com.kartik.product.service.ProductService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {


    //Create Product
    private ProductService productService;
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        System.out.println("under Post Mapping, product Controller");
        ProductDTO productDTOResponse = productService.createProductDTO(productDTO);
        return new ResponseEntity<>(productDTOResponse, HttpStatus.CREATED);
    }

    //getAllProduct
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        System.out.println("under Get Mapping, Products Controller");
        return productService.getAllProducts();

    }
    //get Product by ID
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        System.out.println("under Get Mapping by ID, Category Controller");
        return productService.getProductById(id);
    }

    //delete Product
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

    //update Product
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id, productDTO);
    }

}
