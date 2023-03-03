# Basic Spring Boot CRUD REST API

This repository contains a simple CRUD REST API for managing employees built using Spring Boot and Hibernate. The purpose of this repository is to provide a basic example of how to create a REST API using Spring Boot and Hibernate.

# Table of Contents

- [Endpoints](#endpoints)
    - [Employee](#employee)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

# Endpoints

This REST API provides the following endpoints:

## Employee

1. ### Get all Employees

    This endpoint is used to retrieve a list of all employees.
    
    `GET /api/employees`

2. ### Get Employee by Id

    This endpoint is used to retrieve a specific employee by its ID.
    
    `GET /api/employees/{id}`

3. ### Create an Employee

    This endpoint is used to create new employee.
    
    `POST /api/employees`

4. ### Update an Employee

    This endpoint is used to update a specific employee by its ID.
    
    `PUT /api/employees/{id}`

5. ### Delete an Employee

    This endpoint is used to delete a specific employee by its ID.
    
    `DELETE /api/employees/{id}`

# Installation

1. To run the projects, you'll need to have Java installed on your machine. You can download Java from the [official website](https://www.java.com/en/download/).

2. Install an Integrated Development Environment (IDE) such as IntelliJ  or Visual Studio Code.

   - To install IntelliJ IDEA, follow the instructions on the [official website](https://www.jetbrains.com/idea/download/)

   - To install Visual Studio Code, follow the instructions on the [official website](https://code.visualstudio.com/Download).

# Usage

To run the application, please follow these steps:

1. Clone the repository using the following command:

   `git clone https://github.com/aamirxshaikh/spring-boot-hibernate-rest-api.git`

2. Navigate into the project directory using the following command:

   `cd spring-boot-hibernate-rest-api`

3. You can simply run the project in the IntelliJ IDEA by clicking the "run" icon or you can run the application using the following command:

   `mvn spring-boot:run`

4. After the application is running, you can interact with the API by making HTTP requests to the endpoints listed above using tools like curl or Postman.

# Contributing

Feel free to contribute to this repository by improving the existing codebase or adding new features. Simply fork the repository, make your changes, and create a pull request. I'll be happy to review and merge your changes.

# License

This repository is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

