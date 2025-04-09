package com.restaurant.menuservice.service;

import com.restaurant.menuservice.dto.MenuItemDto;
import com.restaurant.menuservice.model.DietaryRestriction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service interface for menu item operations.
 */
public interface MenuItemService {

    /**
     * Get all menu items.
     *
     * @return a list of all menu item DTOs
     */
    List<MenuItemDto> getAllMenuItems();

    /**
     * Get a menu item by its ID.
     *
     * @param id the menu item ID
     * @return the menu item DTO
     */
    MenuItemDto getMenuItemById(Long id);

    /**
     * Create a new menu item.
     *
     * @param menuItemDto the menu item data
     * @return the created menu item DTO with its assigned ID
     */
    MenuItemDto createMenuItem(MenuItemDto menuItemDto);

    /**
     * Update an existing menu item.
     *
     * @param id the ID of the menu item to update
     * @param menuItemDto the new menu item data
     * @return the updated menu item DTO
     */
    MenuItemDto updateMenuItem(Long id, MenuItemDto menuItemDto);

    /**
     * Delete a menu item by its ID.
     *
     * @param id the ID of the menu item to delete
     */
    void deleteMenuItem(Long id);

    /**
     * Get all menu items in a specific category.
     *
     * @param categoryId the category ID
     * @return a list of menu item DTOs in the specified category
     */
    List<MenuItemDto> getMenuItemsByCategory(Long categoryId);

    /**
     * Get all available menu items.
     *
     * @return a list of all available menu item DTOs
     */
    List<MenuItemDto> getAvailableMenuItems();

    /**
     * Get all menu items with a specific dietary restriction.
     *
     * @param restriction the dietary restriction to filter by
     * @return a list of menu item DTOs that meet the specified dietary restriction
     */
    List<MenuItemDto> getMenuItemsByDietaryRestriction(DietaryRestriction restriction);

    /**
     * Get all menu items within a specified price range.
     *
     * @param minPrice the minimum price (inclusive)
     * @param maxPrice the maximum price (inclusive)
     * @return a list of menu item DTOs within the specified price range
     */
    List<MenuItemDto> getMenuItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Get all menu items that contain a specific ingredient.
     *
     * @param ingredient the ingredient to search for
     * @return a list of menu item DTOs that contain the specified ingredient
     */
    List<MenuItemDto> getMenuItemsByIngredient(String ingredient);
}