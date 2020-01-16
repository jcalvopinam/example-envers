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

1. First you must create the schema, to do that, you can execute the script database.sql.
2. Compile the project with the following command:
```
mvn clean install
```
3. The project is a Spring Boot Application, you can run inside of your ide or
 from terminal with the following command: 
 ```
 mvn spring-boot:run
 ```
4. When starting the application, it will create all the tables defined in the entity classes
* Anyway there is a script with the DDL of tables (tables.sql)


Rest endpoints
---
I used the postman as a client to test the endpoints, you can import the collection, the file is in:
```
/resources/endpoints/collection.json
```
Or if you prefer, you can see the Controllers inside of ```com.jcalvopinam.web``` 
package and the DTOs inside of ```com.jcalvopinam.dto```


Docker
---
The following command will download the mysql image
``` 
docker run --name mysqlDb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=juanca123 -d mysql
```