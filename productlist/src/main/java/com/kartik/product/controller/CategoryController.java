package com.kartik.product.controller;

import com.kartik.product.dto.CategoryDTO;
import com.kartik.product.exception.CategoryAlreadyExistException;
import com.kartik.product.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    
    //Create Category
    private CategoryService categoryService;
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        System.out.println("under Post Mapping, Category Controller");
            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    //getCategories
    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        System.out.println("under Get Mapping, Category Controller");
        return categoryService.getAllCategories();

    }

    //get category by ID
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        System.out.println("under Get Mapping by ID, Category Controller");
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}