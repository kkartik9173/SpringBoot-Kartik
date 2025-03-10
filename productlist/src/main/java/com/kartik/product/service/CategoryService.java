package com.kartik.product.service;

import com.kartik.product.dto.CategoryDTO;
import com.kartik.product.mapper.CategoryMapper;
import com.kartik.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.kartik.product.entity.Category;


@Service
@AllArgsConstructor
public class CategoryService {

    //Create categories
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        System.out.println("Under Category Service");
        Category category = CategoryMapper.toCategoryEntity((categoryDTO));
        category = categoryRepository.save(category);

        return CategoryMapper.toCategoryDTO(category);
    }


    //get all categories

    //get category by id

    //delete category
}
