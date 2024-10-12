# User Management REST API

## Overview
This is a simple REST API for managing users, built with Java 17 and Spring Boot. It provides endpoints to create, list all/specific users, update, and delete users, with data persistence using an H2 database.

## Requirements
- Java 17+
- Maven or Gradle

## Running the Application
1. Clone the repository: `git clone https://github.com/your-repo-link`

2. Navigate to the project directory: `cd user-management`

3. Run the application:
   - For Maven: `mvn spring-boot:run`
   - For Gradle: `./gradlew bootRun`

The application will be available at `http://localhost:8080`.

## API Endpoints (You can use CURL or Postman, I used Postman to conduct these API Endpoint tests)
- `POST /users`: Create a new user.
  - Example request:
    ```json
    {
      "username": "johndoe",
      "firstName": "John",
      "lastName": "Doe",
      "email": "johndoe@example.com",
      "phoneNumber": "123-456-7890"
    }
    ```

- `GET /users`: Retrieve all users.
- `GET /users/{id}`: Retrieve a specific user by ID.
- `PUT /users/{id}`: Update a user's information.
- `DELETE /users/{id}`: Delete a user by ID.
- make sure you replace the curly brackets with the actual number

## Persistence
The application uses an H2 file-based database, ensuring that user data persists across application restarts.


