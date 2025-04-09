package com.restaurant.menuservice.service;

import com.restaurant.menuservice.dto.CategoryDto;

import java.util.List;

/**
 * Service interface for category operations.
 *
 * Defining a service interface allows us to:
 * 1. Clearly define the contract that implementations must follow
 * 2. Easily switch implementations (e.g., for testing or different backends)
 * 3. Apply aspects (like transaction management) at the interface level
 */
public interface CategoryService {

    /**
     * Get all categories.
     *
     * @return a list of all category DTOs
     */
    List<CategoryDto> getAllCategories();

    /**
     * Get a category by its ID.
     *
     * @param id the category ID
     * @return the category DTO
     */
    CategoryDto getCategoryById(Long id);

    /**
     * Create a new category.
     *
     * @param categoryDto the category data
     * @return the created category DTO with its assigned ID
     */
    CategoryDto createCategory(CategoryDto categoryDto);

    /**
     * Update an existing category.
     *
     * @param id the ID of the category to update
     * @param categoryDto the new category data
     * @return the updated category DTO
     */
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);

    /**
     * Delete a category by its ID.
     *
     * @param id the ID of the category to delete
     */
    void deleteCategory(Long id);
}
