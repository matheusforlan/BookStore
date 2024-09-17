# BookStore API

This is a RESTful API for managing a bookstore, including features for user authentication, book catalog, cart, and orders. The system supports two user roles: `ADMIN` and `CUSTOMER`. Each role has different permissions for managing books and orders.

## Requirements

- Java 17
- Maven 3.6+
- Docker (optional, for running the database in a container)

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/bookstore-api.git
cd bookstore-api
```
## 2. Configure the H2 Database
By default, the application uses an in-memory H2 database, which is reset each time the application starts. You can modify the database configuration in the src/main/resources/application.properties file if needed.

## 3.  Run the application
To start the application, run BookStoreApplication in your IDE or use the following command:
```bash
./mvnw spring-boot:run
```
This will start the server on `http://localhost:8080`.

## 4.  Access the H2 Database Console (Optional)
To access the H2 database console, navigate to `http://localhost:8080/h2-db` in your browser. The default credentials are:

- **JDBC URL**: `jdbc:h2:mem:bookstoredb`
- **Username**: `admin`
- **Password**: `123`

# Authentication

## 1. Register Users

To register an `ADMIN` or `CUSTOMER`, use the following API request:

- **POST** `/register`

Example payload for an `ADMIN` user:

```json
{
    "login": "adminUser",
    "password": "admin123",
    "role": "ADMIN"
}
```

Example payload for a `CUSTOMER` user:

```json
{
    "login": "customerUser",
    "password": "customer123",
    "role": "CUSTOMER"
}
```

## 2. Login and Get JWT Token

Use the following request to log in and get a JWT token (these users already exists in database):

- **POST** `/login`

### Example payload for admin:

```json
{
    "login": "forlanAdmin",
    "password": "123456"
}
```
### Example payload for customer:

```json
{
    "login": "forlanCustomer",
    "password": "123456"
}
```
The response will include a JWT token, which you should use for authenticated requests.

## 3. Add Books (ADMIN Only)

Only `ADMIN` users can add books to the catalog. Use the following API request:

- **POST** `/books`

### Example payload:

```json
{
    "name": "Book Title",
    "description": "Book description",
    "price": 80.00,
    "category": "Fantasy",
    "bookType": "physical"
}
```
You must include the JWT token in the Authorization header:

Authorization: Bearer 'JWT Token'

## 4. Delete Books (ADMIN Only)

Only `ADMIN` users can delete books to the catalog. Use the following API request:

- **DELETE** `/books/{id}`
You must include the JWT token in the Authorization header:

Authorization: Bearer 'JWT Token'

## 5. Update Books (ADMIN Only)

Only `ADMIN` users can update books to the catalog. Use the following API request:

- **PUT** `/books/{id}`

### Example payload:

```json
{
    "name": "Book Title",
    "description": "Book description",
    "price": 80.00,
    "category": "Fantasy",
    "bookType": "physical"
}
```
You must include the JWT token in the Authorization header:

Authorization: Bearer 'JWT Token'

## 6. Get All Books

Retrieve a list of books with optional pagination and filtering by category or book type.

- **GET** `/books`

### Query Parameters:
- `page`: (optional) The page number, default is `0`.
- `size`: (optional) The number of results per page, default is `5`.
- `category`: (optional) Filter by book category.
- `bookType`: (optional) Filter by book type (`physical` or `digital`).

### Example request:
```bash
GET /books?page=1&size=10&category=Fantasy&bookType=digital
```
### Example Response:
```json
[
  {
    "id": "book-id-1",
    "name": "Book Title",
    "description": "Book description",
    "price": 80.00,
    "category": "Fantasy",
    "bookType": "physical"
  },
  ...
]
```

## 7. Get Book by ID

Retrieve details of a single book by its ID.

* **GET** `/books/{id}`

### Path Parameter:

* `id`: The unique identifier of the book.

### Example Request:

```bash
GET /books/abc123
```
```json
{
  "id": "abc123",
  "name": "Book Title",
  "description": "Book description",
  "price": 80.00,
  "category": "Fantasy",
  "bookType": "physical"
}
```
## 8. Search Books

Search for books based on a query with pagination.

* **GET** `/books/search`

### Query Parameters:

* `query`: The search query.
* `page`: (optional) The page number, default is `0`.
* `size`: (optional) The number of results per page, default is `5`.

### Example Request:

```bash
GET /books/search?query=Game+of+Thrones&page=0&size=5
```
### Example Response:
```json
[
  {
    "id": "book-id-1",
    "name": "Game of Thrones",
    "description": "An incredible world!",
    "price": 80.00,
    "category": "Fantasy",
    "bookType": "physical"
  },
  ...
]
```

## Cart Controller

### 1. Add to Cart

Add a book to the cart of a customer.

* **POST** `/cart/add/{bookId}`

### Path Parameters:

* `bookId`: The ID of the book to add to the cart.

### Query Parameters:

* `customerId`: The ID of the customer whose cart the book will be added to.

### Example Request:

```bash
POST /cart/add/{bookId}?customerId={customerId}
```
### Example Response:
```json
{
  "id": "cart-id",
  "customer": {
    "id": "customer-id",
    "login": "forlanCustomer",
    "password": "encrypted-password",
    "role": "CUSTOMER"
  },
  "books": [
    {
      "id": "book-id-1",
      "name": "Game of Thrones",
      "description": "An incredible world!",
      "price": 80.00,
      "category": "Fantasy",
      "bookType": "physical"
    }
  ]
}
```

### 2. Get Cart

Retrieve the cart for a customer.

* **GET** `/cart`

### Query Parameters:

* `customerId`: The ID of the customer whose cart is to be retrieved.

### Example Request:

```bash
GET /cart?customerId={customerId}
```

### Example Response:
```json
{
  "id": "cart-id",
  "customer": {
    "id": "customer-id",
    "login": "forlanCustomer",
    "password": "encrypted-password",
    "role": "CUSTOMER"
  },
  "books": [
    {
      "id": "book-id-1",
      "name": "Game of Thrones",
      "description": "An incredible world!",
      "price": 80.00,
      "category": "Fantasy",
      "bookType": "physical"
    }
  ]
}
```

### 3. Checkout

Create a new order by checking out the customer's cart.

* **POST** `/orders/checkout`

### Query Parameters:

* `customerId`: The ID of the customer who is checking out.

### Example Request:

```bash
POST /orders/checkout?customerId={customerId}
```

### Example Response:
```json
{
  "id": "order-id",
  "customer": {
    "id": "customer-id",
    "login": "forlanCustomer",
    "password": "encrypted-password",
    "role": "CUSTOMER"
  },
  "books": [
    {
      "id": "book-id-1",
      "name": "Game of Thrones",
      "description": "An incredible world!",
      "price": 80.00,
      "category": "Fantasy",
      "bookType": "physical"
    }
  ],
  "totalPrice": 80.00,
  "status": "COMPLETED"
}
```
