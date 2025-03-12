package com.kartik.product.service;

import com.kartik.product.dto.CategoryDTO;
import com.kartik.product.exception.CategoryAlreadyExistException;
import com.kartik.product.mapper.CategoryMapper;
import com.kartik.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.kartik.product.entity.Category;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CategoryService {

    //Create categories
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        System.out.println("Under Category Service");
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());
        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyExistException("Category " + categoryDTO.getName() + " already exist!");
        }
        Category category = CategoryMapper.toCategoryEntity((categoryDTO));
        category = categoryRepository.save(category);

        return CategoryMapper.toCategoryDTO(category);
    }


    //get all categories
    public List<CategoryDTO> getAllCategories(){
        List<CategoryDTO> listOfCategoryies = categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toCategoryDTO)
                .toList();
        return listOfCategoryies;
    }
    //get category by id
    public CategoryDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Category Not Found"));

        return CategoryMapper.toCategoryDTO(category);
    }

    //delete category
    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);

        return "Category "+ id + " has deleted successfully";
    }
}
