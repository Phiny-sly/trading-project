# amalitech-challenge

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

### How to Build and Run the Docker Image:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Phiny-sly/amalitech-challenge
   cd amalitech-challenge
   ```
2. **Build the Docker Image:**
   - Make sure you have Docker installed.
   - Navigate to the root directory of the project.
   ```bash
   docker build -t amalitech-challenge .
   ```
3. **Run the Docker Image:**
   ```bash
   docker run -p 8080:8080 amalitech-challenge
   ```
4. **Access the GraphQL API:**
    - The GraphiQL interface can be accessed at `http://localhost:8080/graphiql`.
   
### How to Test the GraphQL API:

- To test GraphQL endpoints requiring authentication, include the JWT token in the header as follows:
  Authorization: Bearer <your-token> in JSON format. Example: {
  "Authorization": "Bearer YOUR_ACCESS_TOKEN"}
- The Jwt token is generated when you sign in to your account. You must first create an account and sign in to obtain the token.