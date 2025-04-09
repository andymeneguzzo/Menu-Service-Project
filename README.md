# Restaurant Menu Service API

A robust Spring Boot REST API for managing a restaurant menu, including categories, menu items, and their dietary restrictions.

## Features

- **Menu Category Management**: Create, read, update, and delete menu categories
- **Menu Item Management**: Full CRUD operations for menu items
- **Advanced Filtering**: Search menu items by:
  - Category
  - Availability
  - Dietary restrictions
  - Price range
  - Ingredients
- **Data Validation**: Robust validation for all API inputs
- **Error Handling**: Standardized error responses
- **Development Mode**: Sample data generation for development and testing

## Technologies

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database** (for development)
- **Lombok**
- **JUnit 5** & **Spring Test** for testing

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/restaurant-menu-service.git
   cd restaurant-menu-service
   ```

2. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

3. The API will be available at `http://localhost:8080`
4. The H2 database console is available at `http://localhost:8080/h2-console` (credentials in application.properties)

## API Endpoints

### Categories

| Method | URL                 | Description                          |
|--------|---------------------|--------------------------------------|
| GET    | /api/categories     | Get all categories                   |
| GET    | /api/categories/{id}| Get a category by ID                 |
| POST   | /api/categories     | Create a new category                |
| PUT    | /api/categories/{id}| Update an existing category          |
| DELETE | /api/categories/{id}| Delete a category                    |

### Menu Items

| Method | URL                                     | Description                                    |
|--------|----------------------------------------|------------------------------------------------|
| GET    | /api/menu-items                        | Get all menu items                             |
| GET    | /api/menu-items/{id}                   | Get a menu item by ID                          |
| POST   | /api/menu-items                        | Create a new menu item                         |
| PUT    | /api/menu-items/{id}                   | Update an existing menu item                   |
| DELETE | /api/menu-items/{id}                   | Delete a menu item                             |
| GET    | /api/menu-items/by-category/{id}       | Get menu items by category                     |
| GET    | /api/menu-items/available              | Get all available menu items                   |
| GET    | /api/menu-items/by-dietary-restriction | Get menu items by dietary restriction          |
| GET    | /api/menu-items/by-price-range         | Get menu items within a price range            |
| GET    | /api/menu-items/by-ingredient          | Get menu items containing a specific ingredient|

## Request and Response Examples

### Create a Category

**Request:**
```json
POST /api/categories
{
  "name": "Desserts",
  "description": "Sweet treats to finish your meal"
}
```

**Response:**
```json
201 Created
{
  "id": 1,
  "name": "Desserts",
  "description": "Sweet treats to finish your meal"
}
```

### Create a Menu Item

**Request:**
```json
POST /api/menu-items
{
  "name": "Tiramisu",
  "description": "Italian coffee-flavored dessert with mascarpone",
  "price": 8.95,
  "available": true,
  "categoryId": 1,
  "dietaryRestrictions": ["VEGETARIAN"],
  "ingredients": ["Ladyfingers", "Mascarpone", "Coffee", "Cocoa"]
}
```

**Response:**
```json
201 Created
{
  "id": 1,
  "name": "Tiramisu",
  "description": "Italian coffee-flavored dessert with mascarpone",
  "price": 8.95,
  "available": true,
  "categoryId": 1,
  "categoryName": "Desserts",
  "dietaryRestrictions": ["VEGETARIAN"],
  "ingredients": ["Ladyfingers", "Mascarpone", "Coffee", "Cocoa"]
}
```

## Data Model

### Category
- `id`: Long
- `name`: String (unique)
- `description`: String

### MenuItem
- `id`: Long
- `name`: String
- `description`: String
- `price`: BigDecimal
- `available`: boolean
- `category`: Category (Many-to-One)
- `dietaryRestrictions`: Set<DietaryRestriction>
- `ingredients`: Set<String>

### DietaryRestriction (Enum)
- `VEGETARIAN`
- `VEGAN`
- `GLUTEN_FREE`
- `DAIRY_FREE`
- `NUT_FREE`
- `HALAL`
- `KOSHER`

## Project Structure

```
src/main/java/com/restaurant/menuservice/
├── MenuServiceApplication.java
├── config/
│   └── DataInitializer.java
├── controller/
│   ├── CategoryController.java
│   └── MenuItemController.java
├── dto/
│   ├── CategoryDto.java
│   └── MenuItemDto.java
├── exception/
│   ├── BadRequestException.java
│   ├── ErrorResponse.java
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── model/
│   ├── Category.java
│   ├── DietaryRestriction.java
│   └── MenuItem.java
├── repository/
│   ├── CategoryRepository.java
│   └── MenuItemRepository.java
└── service/
    ├── CategoryService.java
    ├── CategoryServiceImpl.java
    ├── MenuItemService.java
    └── MenuItemServiceImpl.java
```

## Development

The application uses an in-memory H2 database in development mode with sample data. The sample data is initialized by the `DataInitializer` class when the application runs with the `dev` profile.

To run the application in development mode:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## License

[MIT License](LICENSE)

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
