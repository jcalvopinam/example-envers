# Example JPA Envers
This project demonstrates how to use Hibernate Envers with Spring Boot for entity auditing.
Hibernate Envers allows for a comprehensive history of modifications made to database entities.

## Table of Contents
    Description
    Features
    Requirements
    Installation
    Configuration
    Repository Stars
    Contributing
    License

## Description
This project showcases the integration of Hibernate Envers in a Spring Boot application.
Hibernate Envers provides auditing capabilities, allowing you to track changes and access previous versions of data.

This project is based on `Spring Boot Application 3.2.1`, and it is configured with mysql database.

There are the following entities:

- AuditEnversInfo   --> auditory master table
- Person            --> this entity can be a customer or employee.
- Order
- Product
- OrderDetail

For each table (entity) the framework will create the auditory tables.

## Features
- Automatic entity auditing with Hibernate Envers.
- Query historical data versions.
- Flexible and customizable auditing configuration.
- Complete example of setup and usage in a Spring Boot application.

## Requirements
- Java `17.x` e.g.: `sdk install java 17.0.9-amznsdk install java 17.0.9-amzn`
- Gradle `8.x` e.g. you can use the embedded wrapper `gradlew` or install `sdk install gradle 8.5`
- Docker `26.x`
- MySQL `8.2.x`. _(Check the [Docker section](#Docker))_
- Postman (or any rest client)

## Installation

### Cloning the Repository
To clone the repository, use the following command:

```sh
git clone https://github.com/jcalvopinam/example-envers.git
```

### Building the Project
Navigate to the project directory and build the project using Gradle:

```sh
cd example-envers
./gradlew build
```

## Configuration

### Database Configuration

#### In-Memory
In the `src/main/resources/application.properties` file, configure your database connection.
For example, to use an in-memory H2 database, use:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

#### Docker
- Pull db `docker run --name mysql-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=jcalvopinam -d mysql:8.2.0`
- Start container `docker start mysql-db`
- Stop container `docker stop mysql-db`

### Testing
- To run unit and integration tests `./gradlew test`
- Ensure that all tests pass to confirm that the auditing configuration and other functionalities are working correctly.

### Running the Application
1. Since the current version, it is not necessary create manually the schema,
   when the application startup, it will create the schema and the entities.
   Anyway you can find the script in `src/main/resources/db/scripts/database.sql` as well as the script with the DDL of tables `tables.sql`

2. Compile the project `./gradlew clean build`

3. You can run the application inside your ide from `src/main/java/com/jcalvopinam/SampleEnversApplication.java` or
   from terminal with `./gradlew bootRun`

- The application will be available at `http://localhost:8080`. You can interact with the entities and observe how Envers audits the changes.

### Rest Endpoints
I used the postman application as a client to test the endpoints, you can import the collection:
```sh
ll src/main/resources/endpoints/collection\[postmanv2.1\].json
```

Or if you prefer, you can look at the Controllers inside of `com.jcalvopinam.web` package and the DTOs inside of `com.jcalvopinam.dto`

## Repository Stars
![Outstanding Repository Stars](https://api.star-history.com/svg?repos=jcalvopinam/example-envers&type=Date")

## Contributing
Contributions are welcome.
If you have improvements, bug fixes, or new features, feel free to open a pull request or an issue in this repository.

# License
This project is licensed under the MIT License - see the LICENSE file for details.
