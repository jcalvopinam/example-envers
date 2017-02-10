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

There are the basic rest service to do CRUD for each tables.