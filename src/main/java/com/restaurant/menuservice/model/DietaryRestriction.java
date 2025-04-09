package com.restaurant.menuservice.model;

import lombok.Getter;

/**
 * Enum representing different dietary restrictions that may apply to menu items.
 * Using an enum ensures type safety and provides a predefined set of valid values.
 */

@Getter
public enum DietaryRestriction {
    VEGETARIAN("Suitable for vegetarians"),
    VEGAN("Suitable for vegans"),
    GLUTEN_FREE("Does not contain gluten"),
    DAIRY_FREE("Does not contain dairy"),
    NUT_FREE("Does not contain nuts"),
    HALAL("Prepared according to Islamic law"),
    KOSHER("Prepared according to Jewish dietary laws");

    private final String description;

    DietaryRestriction(String description) {
        this.description = description;
    }
}
