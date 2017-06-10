-----------------------------------------------------------
-- DDL: Data Definition Language
-- Scripts for DROP TABLES IF THEM EXIST
-----------------------------------------------------------

DROP TABLE IF EXISTS `example-envers`.`env_person`;
DROP TABLE IF EXISTS `example-envers`.`env_person`;
DROP TABLE IF EXISTS `example-envers`.`env_person`;
DROP TABLE IF EXISTS `example-envers`.`env_person`;
DROP TABLE IF EXISTS `example-envers`.`env_person`;

-----------------------------------------------------------
-- DDL: Data Definition Language
-- Scripts for CREATE TABLES
-----------------------------------------------------------

-- PERSON TABLE
CREATE TABLE `example-envers`.`env_person` (
  `id`         INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255)     DEFAULT NULL,
  `last_name`  VARCHAR(255)     DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ORDER TABLE
CREATE TABLE `example-envers`.`env_order` (
  `order_id`     INT      NOT NULL AUTO_INCREMENT,
  `customer_id`  INT      NULL,
  `employee_id`  INT      NULL,
  `sale_date`    DATETIME NULL,
  `order_status` INT      NULL,
  PRIMARY KEY (`order_id`),
  INDEX `person_customerId_idx` (`customer_id` ASC),
  INDEX `person_employee_id_idx` (`employee_id` ASC),
  CONSTRAINT `person_customer_id`
  FOREIGN KEY (`customer_id`)
  REFERENCES `example-envers`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `person_employee_id`
  FOREIGN KEY (`employee_id`)
  REFERENCES `example-envers`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- PRODUCT TABLE
CREATE TABLE `example-envers`.`env_product` (
  `product_id`        INT         NOT NULL,
  `name`              VARCHAR(45) NULL,
  `description`       VARCHAR(45) NULL,
  `quantity_per_unit` INT         NULL,
  `unit_price`        DOUBLE      NULL,
  PRIMARY KEY (`product_id`)
);

-- ORDER DETAIL TABLE
CREATE TABLE `example-envers`.`env_order_detail` (
  `order_id`   INT    NOT NULL,
  `product_id` INT    NOT NULL,
  `unit_price` DOUBLE NULL,
  `quantity`   INT    NULL,
  `discount`   DOUBLE NULL,
  PRIMARY KEY (`product_id`, `order_id`),
  INDEX `orders_order_detail_idx` (`order_id` ASC),
  CONSTRAINT `orders_order_detail`
  FOREIGN KEY (`order_id`)
  REFERENCES `example-envers`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `products_order_detail`
  FOREIGN KEY (`product_id`)
  REFERENCES `example-envers`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
