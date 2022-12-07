-----------------------------------------------------------
-- DDL: Data Definition Language
-- Scripts for CREATE TABLES
-----------------------------------------------------------

-- PERSON TABLE
CREATE TABLE `sample-envers`.`env_person` (
  `id`          INT          NOT NULL AUTO_INCREMENT,
  `first_name`  VARCHAR(255) DEFAULT NULL,
  `last_name`   VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- PRODUCT TABLE
CREATE TABLE `sample-envers`.`env_product` (
  `product_id`        INT          NOT NULL AUTO_INCREMENT,
  `description`       VARCHAR(255) DEFAULT NULL,
  `name`              VARCHAR(255) DEFAULT NULL,
  `quantity_per_unit` INT          DEFAULT NULL,
  `unit_price`        DOUBLE       DEFAULT NULL,
  PRIMARY KEY (`product_id`)
);
