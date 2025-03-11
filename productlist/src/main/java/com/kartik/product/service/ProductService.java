package com.kartik.product.service;

import com.kartik.product.dto.CategoryDTO;
import com.kartik.product.dto.ProductDTO;
import com.kartik.product.entity.Category;
import com.kartik.product.entity.Product;
import com.kartik.product.exception.CategoryAlreadyExistException;
import com.kartik.product.exception.CategoryNotFoundException;
import com.kartik.product.mapper.CategoryMapper;
import com.kartik.product.mapper.ProductMapper;
import com.kartik.product.repository.CategoryRepository;
import com.kartik.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    public ProductDTO createProductDTO(ProductDTO productDTO){
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category id " + productDTO.getCategoryId() + " not found in the system"));

        //CategoryAlreadyExistException("Category " + categoryDTO.getName() + " already exist!");

        System.out.println("Under Product Service");

        //DTO -> Entity
        Product product = ProductMapper.toProductEntity(productDTO, category);
        product = productRepository.save(product);

        //Entity -> DTO
        return ProductMapper.toProductDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> listOfProducts = productRepository.findAll()
                .stream()
                .map(ProductMapper::toProductDTO)
                .toList();

        return listOfProducts;
    }

    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Searched product Not Found"));

        return ProductMapper.toProductDTO(product);
    }

    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product "+ id + " has deleted successfully";
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category Not Found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }
}
