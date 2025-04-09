package com.restaurant.menuservice.controller;

import com.restaurant.menuservice.dto.CategoryDto;
import com.restaurant.menuservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing categories.
 *
 * @RestController combines @Controller and @ResponseBody, indicating that
 * the return value of methods should be written directly to the HTTP response body.
 *
 * @RequestMapping("/api/categories") sets the base path for all endpoints in this controller.
 *
 * @RequiredArgsConstructor generates a constructor for all final fields,
 * which is used for dependency injection.
 */

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Get all categories.
     *
     * @return a list of all categories
     */
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    /**
     * Get a category by its ID.
     *
     * @param id the category ID
     * @return the category, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    /**
     * Create a new category.
     *
     * @param categoryDto the category data
     * @return the created category
     */
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    /**
     * Update an existing category.
     *
     * @param id the ID of the category to update
     * @param categoryDto the new category data
     * @return the updated category
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDto categoryDto
    ) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }

    /**
     * Delete a category by its ID.
     *
     * @param id the ID of the category to delete
     * @return no content (204) if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}