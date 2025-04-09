package com.restaurant.menuservice.repository;

import com.restaurant.menuservice.model.DietaryRestriction;
import com.restaurant.menuservice.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository interface for MenuItem entity.
 *
 * Extends JpaRepository to inherit standard CRUD operations and
 * adds custom query methods for our specific business requirements.
 */

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    // TODO all methods/queries
    /**
     * Find all menu items by category ID.
     *
     * @param categoryId the ID of the category
     * @return a list of menu items in the specified category
     */
    List<MenuItem> findByCategoryId(Long categoryId);

    /**
     * Find all available menu items.
     *
     * @return a list of all available menu items
     */
    List<MenuItem> findByAvailableTrue();

    /**
     * Find all menu items with a specific dietary restriction.
     *
     * @param restriction the dietary restriction to filter by
     * @return a list of menu items that meet the specified dietary restriction
     */
    @Query("SELECT m FROM MenuItem m JOIN m.dietaryRestrictions r WHERE r = :restriction")
    List<MenuItem> findByDietaryRestriction(@Param("restriction")DietaryRestriction restriction);

    /**
     * Find all menu items within a specified price range.
     *
     * @param minPrice the minimum price (inclusive)
     * @param maxPrice the maximum price (inclusive)
     * @return a list of menu items within the specified price range
     */
    List<MenuItem> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Find all menu items that contain a specific ingredient.
     *
     * @param ingredient the ingredient to search for
     * @return a list of menu items that contain the specified ingredient
     */
    @Query("SELECT m FROM MenuItem m JOIN m.ingredients i WHERE LOWER(i) LIKE LOWER(CONCAT('%', :ingredient, '%'))")
    List<MenuItem> findByIngredientContainingIgnoreCase(@Param("ingredient") String ingredient);
}
