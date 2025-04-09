package com.restaurant.menuservice.dto;

import com.restaurant.menuservice.model.DietaryRestriction;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO (Data Transfer Object) for MenuItem.
 *
 * Contains all the validation constraints required for our API.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDto {

    private Long id;

    @NotBlank(message = "Menu item is required")
    @Size(min = 2, max = 100, message = "Menu item name must be between 2 and 100 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "price must be greater than 0.01")
    @Digits(integer = 8, fraction = 2, message = "Price must have max 8 integer digits and 2 decimal digits")
    private BigDecimal price;

    private boolean available = true;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    private String categoryName;

    private Set<DietaryRestriction> dietaryRestrictions = new HashSet<>();

    private Set<String> ingredients = new HashSet<>();
}