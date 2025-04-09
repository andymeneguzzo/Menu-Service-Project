package com.restaurant.menuservice.controller;

import com.restaurant.menuservice.dto.MenuItemDto;
import com.restaurant.menuservice.model.DietaryRestriction;
import com.restaurant.menuservice.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller for managing menu items.
 */

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    /**
     * Get all menu items.
     *
     * @return a list of all menu items
     */
    @GetMapping
    public ResponseEntity<List<MenuItemDto>> getAllMenuItems() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }

    /**
     * Get a menu item by its ID.
     *
     * @param id the menu item ID
     * @return the menu item, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDto> getMenuItemById(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.getMenuItemById(id));
    }

    /**
     * Create a new menu item.
     *
     * @param menuItemDto the menu item data
     * @return the created menu item
     */
    @PostMapping
    public ResponseEntity<MenuItemDto> createMenuItem(@Valid @RequestBody MenuItemDto menuItemDto) {
        return new ResponseEntity<>(menuItemService.createMenuItem(menuItemDto), HttpStatus.CREATED);
    }

    /**
     * Update an existing menu item.
     *
     * @param id the ID of the menu item to update
     * @param menuItemDto the new menu item data
     * @return the updated menu item
     */
    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDto> updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody MenuItemDto menuItemDto
    ) {
        return ResponseEntity.ok(menuItemService.updateMenuItem(id, menuItemDto));
    }

    /**
     * Delete a menu item by its ID.
     *
     * @param id the ID of the menu item to delete
     * @return no content (204) if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all menu items in a specific category.
     *
     * @param categoryId the category ID
     * @return a list of menu items in the specified category
     */
    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<MenuItemDto>> getMenuItemByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(menuItemService.getMenuItemsByCategory(categoryId));
    }

    /**
     * Get all available menu items.
     *
     * @return a list of all available menu items
     */
    @GetMapping("/available")
    public ResponseEntity<List<MenuItemDto>> getAvailableMenuItems() {
        return ResponseEntity.ok(menuItemService.getAvailableMenuItems());
    }

    /**
     * Get all menu items with a specific dietary restriction.
     *
     * @param restriction the dietary restriction to filter by
     * @return a list of menu items that meet the specified dietary restriction
     */
    @GetMapping("/by-dietary-restriction")
    public ResponseEntity<List<MenuItemDto>> getMenuItemsByDietaryRestriction(
            @RequestParam DietaryRestriction restriction
            ) {
        return ResponseEntity.ok(menuItemService.getMenuItemsByDietaryRestriction(restriction));
    }

    /**
     * Get all menu items within a specified price range.
     *
     * @param minPrice the minimum price (inclusive)
     * @param maxPrice the maximum price (inclusive)
     * @return a list of menu items within the specified price range
     */
    @GetMapping("/by-price-range")
    public ResponseEntity<List<MenuItemDto>> getMenuItemsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice
            ) {
        return ResponseEntity.ok(menuItemService.getMenuItemsByPriceRange(minPrice, maxPrice));
    }

    /**
     * Get all menu items that contain a specific ingredient.
     *
     * @param ingredient the ingredient to search for
     * @return a list of menu items that contain the specified ingredient
     */
    @GetMapping("/by-ingredient")
    public ResponseEntity<List<MenuItemDto>> getMenuItemsByIngredient(
            @RequestParam String ingredient
    ) {
        return ResponseEntity.ok(menuItemService.getMenuItemsByIngredient(ingredient));
    }
}