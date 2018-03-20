Example JPA Envers
---
In this example you can see how to use Envers for Auditing your tables.

This is a Spring Boot Application and it is configured with mysql database.

There are the following entities:

0. AuditEnversInfo --> auditory master table
1. Person --> this entity can be a customer or employee.
2. Order
3. Product
4. OrderDetail

For each table (entity) the framework will create the auditory tables.

How to run?
---

1. Since the current version, it is not necessary create manually the schema,
when the application startup, it will create the schema and the entities.
Anyway you can find the script in `src/main/resources/scripts/database.sql` as well as the script with the DDL of tables `tables.sql`

2. Compile the project with the following command:
   ```
   mvn clean install
   ```
3. You can run the application inside of your ide from `com.jcalvopinam.ExampleEnversApplication.java` or
 from terminal with the following command: 
    ```
    mvn spring-boot:run
    ```


Rest endpoints
---
I used the postman as a client to test the endpoints, you can import the collection, the file is in:
```
/resources/endpoints/collection[postmanv2.1].json
```
Or if you prefer, you can see the Controllers inside of ```com.jcalvopinam.web``` 
package and the DTOs inside of ```com.jcalvopinam.dto```
