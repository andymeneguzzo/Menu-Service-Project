package com.restaurant.menuservice.service;

import com.restaurant.menuservice.dto.MenuItemDto;
import com.restaurant.menuservice.exception.ResourceNotFoundException;
import com.restaurant.menuservice.model.Category;
import com.restaurant.menuservice.model.DietaryRestriction;
import com.restaurant.menuservice.model.MenuItem;
import com.restaurant.menuservice.repository.CategoryRepository;
import com.restaurant.menuservice.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the MenuItemService interface.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Convert a MenuItem entity to a MenuItemDto.
     */
    private MenuItemDto mapToDto(MenuItem menuItem) {
        return MenuItemDto.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .available(menuItem.isAvailable())
                .categoryId(menuItem.getCategory() != null ? menuItem.getCategory().getId() : null)
                .categoryName(menuItem.getCategory() != null ? menuItem.getCategory().getName() : null)
                .dietaryRestrictions(menuItem.getDietaryRestrictions())
                .ingredients(menuItem.getIngredients())
                .build();
    }

    /**
     * Convert a MenuItemDto to a MenuItem entity.
     */
    private MenuItem mapToEntity(MenuItemDto menuItemDto) {
        MenuItem menuItem = MenuItem.builder()
                .name(menuItemDto.getName())
                .description(menuItemDto.getDescription())
                .price(menuItemDto.getPrice())
                .available(menuItemDto.isAvailable())
                .build();

        // set dietary restrictions
        if(menuItemDto.getDietaryRestrictions() != null) {
            menuItemDto.getDietaryRestrictions().forEach(menuItem::addDietaryRestriction);
        }

        // set ingredients
        if(menuItemDto.getIngredients() != null) {
            menuItemDto.getIngredients().forEach(menuItem::addIngredient);
        }

        return menuItem;
    }


    @Override
    @Transactional(readOnly = true)
    public List<MenuItemDto> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MenuItemDto getMenuItemById(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem", "id", id));

        return mapToDto(menuItem);
    }

    @Override
    public MenuItemDto createMenuItem(MenuItemDto menuItemDto) {

        // verify category exists
        Category category = categoryRepository.findById(menuItemDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", menuItemDto.getCategoryId()));

        // map dto to entity
        MenuItem menuItem = mapToEntity(menuItemDto);

        // set category
        menuItem.setCategory(category);

        // save menu item
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        return mapToDto(savedMenuItem);
    }

    @Override
    public MenuItemDto updateMenuItem(Long id, MenuItemDto menuItemDto) {

        // check if the menu item exists
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MenuItem", "id", id));

        // verify category exists
        Category category = categoryRepository.findById(menuItemDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", menuItemDto.getCategoryId()));

        // update menu item
        menuItem.setName(menuItemDto.getName());
        menuItem.setDescription(menuItemDto.getDescription());
        menuItem.setPrice(menuItemDto.getPrice());
        menuItem.setAvailable(menuItemDto.isAvailable());
        menuItem.setCategory(category);

        // update dietary restrictions
        menuItem.getDietaryRestrictions().clear();
        if(menuItemDto.getDietaryRestrictions() != null) {
            menuItemDto.getDietaryRestrictions().forEach(menuItem::addDietaryRestriction);
        }

        // update ingredients
        menuItem.getIngredients().clear();
        if(menuItemDto.getIngredients() != null) {
            menuItemDto.getIngredients().forEach(menuItem::addIngredient);
        }

        // save the updated menu item
        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);

        return mapToDto(updatedMenuItem);

    }

    @Override
    public void deleteMenuItem(Long id) {

        // check if menu item exists
        if(!menuItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("MenuItem", "id", id);
        }

        menuItemRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItemDto> getMenuItemsByCategory(Long categoryId) {

        // verify category exists
        if(!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category", "id", categoryId);
        }

        return menuItemRepository.findByCategoryId(categoryId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItemDto> getAvailableMenuItems() {
        return menuItemRepository.findByAvailableTrue().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItemDto> getMenuItemsByDietaryRestriction(DietaryRestriction restriction) {
        return menuItemRepository.findByDietaryRestriction(restriction).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItemDto> getMenuItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return menuItemRepository.findByPriceBetween(minPrice, maxPrice).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuItemDto> getMenuItemsByIngredient(String ingredient) {
        return menuItemRepository.findByIngredientContainingIgnoreCase(ingredient).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}