package com.kartik.product.controller;

import com.kartik.product.dto.CategoryDTO;
import com.kartik.product.exception.CategoryAlreadyExistException;
import com.kartik.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name="Category Rest API Crud Operation",
        description="Create, Read Update and Delete for Category REST API"
)
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    
    //Create Category
    private CategoryService categoryService;

    @Operation(
            summary = "Create category",
            description = "REST API to create category"
    )
    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        System.out.println("under Post Mapping, Category Controller");
            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    //getCategories
    @Operation(
            summary = "Fetch all categories",
            description = "REST API to fetch all categories"
    )
    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        System.out.println("under Get Mapping, Category Controller");
        return categoryService.getAllCategories();

    }

    //get category by ID
    @Operation(
            summary = "Fetch category by Id",
            description = "REST API to fetch category by Id"
    )
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        System.out.println("under Get Mapping by ID, Category Controller");
        return categoryService.getCategoryById(id);
    }

    @Operation(
            summary = "Delete category by Id",
            description = "REST API to delete category by Id"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}