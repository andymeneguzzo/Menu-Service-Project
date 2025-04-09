package com.restaurant.menuservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a menu category (e.g., Appetizers, Main Courses, Desserts).
 * Uses Lombok annotations to reduce boilerplate code:
 * - @Data: Generates getters, setters, equals, hashCode, and toString methods
 * - @Builder: Implements the Builder pattern
 * - @NoArgsConstructor/@AllArgsConstructor: Generates constructors
 */

@Entity
@Table(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // must be defined for nullable=false, and unique=true

    private String description;

    // We use mappedBy to indicate that the Category is not the owner of the relationship
    // CascadeType.ALL means all operations (PERSIST, MERGE, REMOVE, REFRESH, DETACH) will be cascaded
    // orphanRemoval = true means that if a MenuItem is removed from the collection, it will be deleted
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuItem> menuItems = new HashSet<>();

    // Helper method to add a menu item to this category
    // This ensures both sides of the relationship are properly maintained
    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
        menuItem.setCategory(this);
    }

    // Helper method to remove a menu item from this category
    public void removeMenuItem(MenuItem menuItem) {
        menuItems.remove(menuItem);
        menuItem.setCategory(null);
    }
}
