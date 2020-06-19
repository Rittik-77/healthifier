CREATE DATABASE IF NOT EXISTS `healthifier_db`;
USE `healthifier_db`;

DROP TABLE IF EXISTS `food`;
DROP TABLE IF EXISTS `quantity_enum`;

CREATE TABLE `quantity_enum`(
	`qty_enum` VARCHAR(7),
    PRIMARY KEY (`qty_enum`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `quantity_enum` VALUES ('GRAM');
INSERT INTO `quantity_enum` VALUES ('ML');
INSERT INTO `quantity_enum` VALUES ('NUMBER');

CREATE TABLE `food` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL UNIQUE,
    `qty_enum` VARCHAR(7) NOT NULL,
    `qty` REAL NOT NULL,
    `calories` REAL NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_qty_enum` FOREIGN KEY (`qty_enum`) REFERENCES `quantity_enum`(`qty_enum`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Insert starter data into food DB
--

INSERT INTO `food` VALUES
	(1, 'Paneer Butter Masala', 'GRAM', 100, 229),
    (2, 'Masala Dosa', 'NUMBER', 1, 498),
    (3, 'Coke', 'ML', 360, 140);
