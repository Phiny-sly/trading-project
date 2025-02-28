# Trading Project

## Description
This project is a Java Spring Boot application that provides a GraphQL API for users to place orders on various products. The application is containerized using Docker, making it easy to deploy and run in a consistent environment. Testing can be performed using GraphiQL, and no front-end is required.

### Features:

1. **User Registration:**
    - Users can register for an account with the application.

2. **User Sign-In:**
    - Registered users can sign in to their accounts.

3. **Product Creation:**
    - Registered users can create new products using GraphQL mutations.
   
4. **Order Placement:**
    - Users can place orders for different products using GraphQL queries.
    
5. **GraphiQL Testing:**
    - Testing and exploring the GraphQL API can be done conveniently using GraphiQL.

### Technologies Used:

- **Backend:**
    - [Java Spring Boot](https://spring.io/projects/spring-boot): A Java-based framework for building robust and scalable backend applications.
    - [GraphQL](https://graphql.org/): A query language for APIs that provides a more efficient and powerful alternative to traditional REST.
    - [JWT](https://jwt.io/): JSON Web Tokens for authentication.

- **Database:**
    - [H2 Database](https://www.h2database.com/): A lightweight, in-memory database for development and testing.

- **Containerization:**
    - [Docker](https://www.docker.com/): Containerization platform for packaging applications and their dependencies.
    
- **Build and Dependency Management:** 
    - [Maven](https://maven.apache.org/): Dependency management tool used to streamline the build process.

### How to Build and Run the Docker Image:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Phiny-sly/amalitech-challenge
   cd amalitech-challenge
   ```
2. **Install Maven Wrapper:**
   - Ensure you have Maven installed and added to your system's `PATH`.
   - If you encounter an error while building the Docker image, run the following command to install Maven Wrapper locally:
   ```bash
    mvn -N io.takari:maven:wrapper
   ```
3. **Build the Docker Image:**
   - Make sure you have Docker installed and running.
   - Navigate to the root directory of the project.
   ```bash
   docker build -t amalitech-challenge .
   ```
4. **Run the Docker Image:**
   ```bash
   docker run -p 8080:8080 amalitech-challenge
   ```
5. **Access the GraphQL API:**
    - The GraphiQL interface can be accessed at `http://localhost:8080/graphiql`.

### GraphQL API Overview:

#### Query Types:

- **getAllUsers:**
    - Returns a list of all users.

- **getUserById(id):**
    - Returns details for a specific user identified by the provided ID.

- **getAllProducts:**
    - Returns a list of all products.

- **getProductsByOrderId(id):**
    - Returns a list of products associated with a specific order ID.

- **getAllOrders:**
    - Returns a list of all orders.

- **getOrderById(id):**
    - Returns details for a specific order identified by the provided ID.

- **getOrdersByUserId(id):**
    - Returns a list of orders associated with a specific user ID.

#### Mutation Types:

- **createUser(input):**
    - Creates a new user based on the provided input.

- **updateUser(id, input):**
    - Updates user details for the user identified by the provided ID.

- **deleteUser(id):**
    - Deletes a user identified by the provided ID.

- **createProduct(input):**
    - Creates a new product based on the provided input.

- **updateProduct(id, input):**
    - Updates product details for the product identified by the provided ID.

- **deleteProduct(id):**
    - Deletes a product identified by the provided ID.

- **createOrder(input, userId):**
    - Creates a new order based on the provided input and user ID.

- **updateOrder(id, input):**
    - Updates order details for the order identified by the provided ID.

- **deleteOrder(id):**
    - Deletes an order identified by the provided ID.

- **login(input):**
    - Performs user authentication and returns authentication details.

#### GraphQL Types:

- **UserDto:**
    - Represents detail for a user.
        - `id: ID!`
        - `name: String!`
        - `email: String!`
        - `role: Role!`

- **ProductDto:**
    - Represents details for a product.
        - `id: ID!`
        - `productName: String!`
        - `price: Float!`
        - `stock: Int!`

- **OrderDto:**
    - Represents details for an order.
        - `id: ID!`
        - `userId: Int!`
        - `email: String!`
        - `listOfProductLines: [ProductLineDto!]!`

- **ProductLineDto:**
    - Represents details for a product line within an order.
        - `productId: Float!`
        - `unitPrice: Float!`
        - `productName: String!`
        - `quantity: Int!`
        
- **AuthDto:**
    - Represents authentication details.
        - `token: String!`

#### Input Types:

- **UserPayload:**
    - Represents input for creating or updating a user.
        - `name: String`
        - `email: String`
        - `role: Role`
        - `password: String`

- **ProductPayload:**
    - Represents input for creating or updating a product.
        - `name: String`
        - `price: Float`
        - `stock: Int`

- **OrderPayload:**
    - Represents input for creating or updating an order.
        - `listOfProductLines: [ProductLinePayload!]`

- **ProductLinePayload:**
    - Represents input for creating or updating a product line within an order.
        - `productId: Int!`
        - `quantity: Int!`

- **AuthPayload:**
    - Represents input for user authentication.
        - `email: String!`
        - `password: String!`

#### Enum Type:

- **Role:**
    - Represents user roles.
        - `ADMIN`
        - `USER`

#### How to Use:

- Use these GraphQL queries, mutations, and types to interact with the API.
- For mutations that require authentication, include the JWT token in the header as follows:
- To test GraphQL endpoints requiring authentication, include the JWT token in the header as follows:
  Authorization: Bearer <your-token> in JSON format. Example: {
  "Authorization": "Bearer YOUR_ACCESS_TOKEN"}
- The Jwt token is generated when you sign in to your account. You must first create an account and sign in to obtain the token.

#### Accessing H2 Console

To access the H2 Console for database management, follow these steps:

1. **Start your Spring Boot application.**

2. **Open a web browser and go to [http://localhost:8080/h2-console](http://localhost:8080/h2-console).**

3. **Use the following H2 database connection settings:**
    - **JDBC URL:** `jdbc:h2:mem:testdb`
    - **Username:** `sa`
    - **Password:** (use password in application.properties)

4. **Click the "Connect" button.**

5. **Explore and manage your H2 database using the H2 Console.**
