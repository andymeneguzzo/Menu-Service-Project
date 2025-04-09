package com.restaurant.menuservice.config;

import com.restaurant.menuservice.model.Category;
import com.restaurant.menuservice.model.DietaryRestriction;
import com.restaurant.menuservice.model.MenuItem;
import com.restaurant.menuservice.repository.CategoryRepository;
import com.restaurant.menuservice.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Configuration class for initializing sample data.
 *
 * This bean is only active in the "dev" profile to avoid
 * loading sample data in production.
 */
@Configuration
@RequiredArgsConstructor
@Profile("dev")  // Only active in the dev profile
public class DataInitializer {

    private final CategoryRepository categoryRepository;
    private final MenuItemRepository menuItemRepository;

    /**
     * Initialize sample data for development and testing.
     */
    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Create categories
            Category starters = createCategory("Starters", "Appetizers and small dishes");
            Category mainCourses = createCategory("Main Courses", "Hearty main dishes");
            Category desserts = createCategory("Desserts", "Sweet treats to finish your meal");
            Category beverages = createCategory("Beverages", "Refreshing drinks");

            // Create menu items

            // Starters
            createMenuItem(
                    "Bruschetta",
                    "Toasted bread with fresh tomatoes, garlic, and basil",
                    new BigDecimal("7.95"),
                    true,
                    starters,
                    Set.of(DietaryRestriction.VEGETARIAN),
                    Set.of("Bread", "Tomatoes", "Garlic", "Basil", "Olive oil")
            );

            createMenuItem(
                    "Calamari",
                    "Fried squid rings with lemon and aioli",
                    new BigDecimal("9.95"),
                    true,
                    starters,
                    Set.of(),
                    Set.of("Squid", "Flour", "Lemon", "Aioli")
            );

            createMenuItem(
                    "Caprese Salad",
                    "Fresh mozzarella, tomatoes, and basil with balsamic glaze",
                    new BigDecimal("8.95"),
                    true,
                    starters,
                    Set.of(DietaryRestriction.VEGETARIAN, DietaryRestriction.GLUTEN_FREE),
                    Set.of("Mozzarella", "Tomatoes", "Basil", "Balsamic glaze", "Olive oil")
            );

            // Main Courses
            createMenuItem(
                    "Spaghetti Carbonara",
                    "Classic pasta with pancetta, egg, and parmesan",
                    new BigDecimal("14.95"),
                    true,
                    mainCourses,
                    Set.of(),
                    Set.of("Spaghetti", "Pancetta", "Eggs", "Parmesan", "Black pepper")
            );

            createMenuItem(
                    "Vegetable Curry",
                    "Seasonal vegetables in a spicy curry sauce with rice",
                    new BigDecimal("13.95"),
                    true,
                    mainCourses,
                    Set.of(DietaryRestriction.VEGETARIAN, DietaryRestriction.VEGAN, DietaryRestriction.GLUTEN_FREE),
                    Set.of("Seasonal vegetables", "Curry spices", "Coconut milk", "Rice")
            );

            createMenuItem(
                    "Grilled Salmon",
                    "Fresh salmon with lemon butter, served with asparagus",
                    new BigDecimal("19.95"),
                    true,
                    mainCourses,
                    Set.of(DietaryRestriction.GLUTEN_FREE),
                    Set.of("Salmon", "Lemon", "Butter", "Asparagus", "Herbs")
            );

            // Desserts
            createMenuItem(
                    "Tiramisu",
                    "Italian coffee-flavored dessert with mascarpone",
                    new BigDecimal("8.95"),
                    true,
                    desserts,
                    Set.of(DietaryRestriction.VEGETARIAN),
                    Set.of("Ladyfingers", "Mascarpone", "Coffee", "Cocoa")
            );

            createMenuItem(
                    "Chocolate Fondant",
                    "Warm chocolate cake with a molten center, served with vanilla ice cream",
                    new BigDecimal("9.95"),
                    true,
                    desserts,
                    Set.of(DietaryRestriction.VEGETARIAN),
                    Set.of("Chocolate", "Butter", "Eggs", "Flour", "Sugar", "Vanilla ice cream")
            );

            // Beverages
            createMenuItem(
                    "Fresh Lemonade",
                    "Homemade lemonade with fresh mint",
                    new BigDecimal("4.95"),
                    true,
                    beverages,
                    Set.of(DietaryRestriction.VEGETARIAN, DietaryRestriction.VEGAN, DietaryRestriction.GLUTEN_FREE, DietaryRestriction.DAIRY_FREE),
                    Set.of("Lemons", "Sugar", "Mint", "Water")
            );

            createMenuItem(
                    "Espresso",
                    "Strong Italian coffee",
                    new BigDecimal("2.95"),
                    true,
                    beverages,
                    Set.of(DietaryRestriction.VEGETARIAN, DietaryRestriction.VEGAN, DietaryRestriction.GLUTEN_FREE, DietaryRestriction.DAIRY_FREE),
                    Set.of("Coffee beans")
            );

            System.out.println("Sample data initialized successfully");
        };
    }

    /**
     * Helper method to create a category.
     */
    private Category createCategory(String name, String description) {
        Category category = Category.builder()
                .name(name)
                .description(description)
                .build();

        return categoryRepository.save(category);
    }

    /**
     * Helper method to create a menu item.
     */
    private void createMenuItem(String name, String description, BigDecimal price, boolean available,
                                Category category, Set<DietaryRestriction> restrictions, Set<String> ingredients) {
        MenuItem menuItem = MenuItem.builder()
                .name(name)
                .description(description)
                .price(price)
                .available(available)
                .dietaryRestrictions(restrictions)
                .ingredients(ingredients)
                .category(category)
                .build();

        menuItemRepository.save(menuItem);
    }
}
