package com.restaurant.menuservice.repository;

import com.restaurant.menuservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Category entity.
 *
 * Spring Data JPA will automatically implement this interface at runtime,
 * providing standard CRUD operations with the database.
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Find a category by its name (case-insensitive).
     *
     * @param name the category name to search for
     * @return an Optional containing the category if found, or empty if not found
     */
    Optional<Category> findByNameIgnoreCase(String name);

    /**
     * Check if a category exists with the given name (case-insensitive).
     *
     * @param name the category name to check
     * @return true if a category with the given name exists, false otherwise
     */
    boolean existsByNameIgnoreCase(String name);
}
