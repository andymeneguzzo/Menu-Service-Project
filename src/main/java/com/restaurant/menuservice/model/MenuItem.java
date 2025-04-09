package com.restaurant.menuservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "menu_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private boolean available = true;

    // Many-to-One relationship with Category
    // FetchType.LAZY means the category will only be loaded when explicitly accessed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // Store dietary restrictions as a collection of enum values
    // @ElementCollection is used for simple collections of basic or embeddable types
    // @Enumerated(EnumType.STRING) stores the enum values as strings in the database
    @ElementCollection
    @CollectionTable(name = "menu_item_dietary_restrictions", joinColumns = @JoinColumn(name = "menu_item_id"))
    @Column(name = "restriction")
    @Enumerated(EnumType.STRING)
    private Set<DietaryRestriction> dietaryRestrictions = new HashSet<>();

    // Store ingredients as a simple collection of strings
    @ElementCollection
    @CollectionTable(name = "menu_item_ingredients", joinColumns = @JoinColumn(name = "menu_item_id"))
    @Column(name = "ingredient")
    private Set<String> ingredients = new HashSet<>();

    // Helper methods to manage dietary restrictions
    public void addDietaryRestriction(DietaryRestriction restriction) {
        dietaryRestrictions.add(restriction);
    }

    public void removeDietaryRestriction(DietaryRestriction restriction) {
        dietaryRestrictions.remove(restriction);
    }

    // Helper methods to manage ingredients
    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(String ingredient) {
        ingredients.remove(ingredient);
    }
}
