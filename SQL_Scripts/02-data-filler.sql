CREATE DATABASE IF NOT EXISTS `healthifier_db`;
USE `healthifier_db`;

--
-- Drop Existing Tables and Create Afresh
--

DROP TABLE IF EXISTS `food`;
DROP TABLE IF EXISTS `quantity_enum`;

CREATE TABLE `quantity_enum`(
	`qty_enum` VARCHAR(7),
    PRIMARY KEY (`qty_enum`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Insert values in quantity_enum table
--

INSERT INTO `quantity_enum` VALUES ('GRAM');
INSERT INTO `quantity_enum` VALUES ('ML');
INSERT INTO `quantity_enum` VALUES ('NUMBER');

CREATE TABLE `food` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL UNIQUE,
    `qty_enum` VARCHAR(7) NOT NULL,
    `qty` REAL NOT NULL,
    `calories` REAL NOT NULL,
    `image_url` VARCHAR(200) NOT NULL, 
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_qty_enum` FOREIGN KEY (`qty_enum`) REFERENCES `quantity_enum`(`qty_enum`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Insert starter data into food DB
--

INSERT INTO `food`(`name`, `qty_enum`, `qty`, `calories`, `image_url`) VALUES
	('Tomato Soup Without Cream', 'GRAM', 100, 30, 'https://m.tarladalal.com/members/9306/big/big_healthy_indian_tomato_soup-14087.jpg'),
	('Tomato Soup With Cream', 'GRAM', 254, 142, 'https://www.nutralite.com/manage/uploads/recipe_banners/banner_32.png'),
	('Vegetable Soup Without Cream', 'GRAM', 241, 79.5, 'https://i.ytimg.com/vi/9X0BcEABsss/maxresdefault.jpg'),
	('Vegetable Soup With Cream', 'GRAM', 267, 334.5, 'https://www.recipetineats.com/wp-content/uploads/2018/09/Cream-of-Vegetable-Soup-with-Noodles.jpg'),
	('Sweet Corn Veg Soup', 'GRAM', 251, 122, 'https://www.sailusfood.com/wp-content/uploads/2012/02/sweet-corn-veg-soup.jpg'),
	('Sweet Corn Chicken Soup', 'GRAM', 430, 251, 'https://www.ndtv.com/cooks/images/chicken.sweet.corn.soup.jpg'),

	('Paneer Butter Masala', 'GRAM', 100, 229, ''),
    
    ('Masala Dosa', 'NUMBER', 1, 498, ''),
    
    ('Coke', 'ML', 360, 140, '');
