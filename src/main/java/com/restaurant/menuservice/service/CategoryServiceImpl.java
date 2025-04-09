package com.restaurant.menuservice.service;

import com.restaurant.menuservice.dto.CategoryDto;
import com.restaurant.menuservice.exception.BadRequestException;
import com.restaurant.menuservice.exception.ResourceNotFoundException;
import com.restaurant.menuservice.model.Category;
import com.restaurant.menuservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the CategoryService interface.
 *
 * @Service marks this as a Spring service bean
 * @RequiredArgsConstructor generates a constructor for all final fields
 * @Transactional ensures database operations occur within a transaction
 */

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    // Map the repo to the dto
    private CategoryDto mapToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    /**
     * Convert a CategoryDto to a Category entity.
     */
    private Category mapToEntity(CategoryDto categoryDto) {
        return Category.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
    }


    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category not found for id = " + id));

        return mapToDto(category);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        // check if category with same name already exists
        if(categoryRepository.existsByNameIgnoreCase(categoryDto.getName())) {
            throw new BadRequestException("a category with the same name already exists");
        }

        Category category = mapToEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);

        return mapToDto(category);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {

        // check if category exists
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category not found for id = " + id));

        // check if the new name is already taken by another category
        if(!category.getName().equalsIgnoreCase(categoryDto.getName()) &&
            categoryRepository.existsByNameIgnoreCase(categoryDto.getName())) {
            throw new BadRequestException("A category with the same name: " + categoryDto.getName() + " already exists");
        }

        // update the category
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return mapToDto(category);
    }

    @Override
    public void deleteCategory(Long id) {

        // check exists category
        if(!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category", "id", id);
        }

        categoryRepository.deleteById(id);
    }
}
