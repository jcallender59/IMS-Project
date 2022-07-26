DROP SCHEMA IF EXISTS `ims`;
CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `order_items`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
	`item_id` INT(11) NOT NULL AUTO_INCREMENT,
	`item_name` VARCHAR(40) NOT NULL,
	`price` DOUBLE NOT NULL,
	PRIMARY KEY (`item_id`)
	);


CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` INT(11) NOT NULL AUTO_INCREMENT,
  `item_id` INT(11) DEFAULT NOT NULL,
  `customer_id` INT(11) DEFAULT NOT NULL,
  `quantity` INT (11) DEFAULT NOT NULL,
  PRIMARY KEY (`order_id`),
  FOREIGN KEY (`customer_id`) REFERENCES customers (`id`),
  FOREIGN KEY (`item_id`) REFERENCES items (`item_id`)
);

CREATE TABLE IF NOT EXISTS `orders_items` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `order_id` INT(11) DEFAULT NOT NULL,
  `item_id` INT(11) DEFAULT NOT NULL,
  `quantity` INT(11) DEFAULT NOT NULL,
  `customer_id` INT(11) DEFAULT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`item_id`) REFERENCES items (`item_id`),
  FOREIGN KEY (`order_id`) REFERENCES orders (`order_id`),
  FOREIGN KEY (`customer_id`) REFERENCES customers (`id`)
  );

  SET FOREIGN_KEY_CHECKS = 1;