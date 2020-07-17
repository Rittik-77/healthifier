	CREATE DATABASE IF NOT EXISTS `healthifier_db`;
	USE `healthifier_db`;

	--
	-- Drop Existing Tables and Create Afresh
	--

	DROP TABLE IF EXISTS `workout_tracker`;
	DROP TABLE IF EXISTS `food_tracker`;
	DROP TABLE IF EXISTS `user`;
    DROP TABLE IF EXISTS `role_enum`;
	DROP TABLE IF EXISTS `workout`;
	DROP TABLE IF EXISTS `food`;
	DROP TABLE IF EXISTS `quantity_enum`;
    
    --
    -- Create Tables Again from scratch
    --

	CREATE TABLE IF NOT EXISTS `quantity_enum`(
		`qty_enum` VARCHAR(7),
		PRIMARY KEY (`qty_enum`)
	)ENGINE=InnoDB DEFAULT CHARSET=latin1;

	--
	-- Insert values in quantity_enum table
	--

	INSERT INTO `quantity_enum` VALUES ('GRAM');
	INSERT INTO `quantity_enum` VALUES ('ML');
	INSERT INTO `quantity_enum` VALUES ('NUMBER');
    
    CREATE TABLE IF NOT EXISTS `role_enum`(
		`role` VARCHAR(7),
        PRIMARY KEY(`role`)
    )ENGINE=InnoDB DEFAULT CHARSET=latin1;
    
    INSERT INTO `role_enum` VALUES ('ADMIN');
    INSERT INTO `role_enum` VALUES ('USER');

	CREATE TABLE IF NOT EXISTS `food` (
		`name` VARCHAR(50) ,
		`qty_enum` VARCHAR(7) NOT NULL,
		`qty` REAL NOT NULL,
		`calories` REAL NOT NULL,
		`image_url` VARCHAR(200) NOT NULL, 
		PRIMARY KEY (`name`),
		CONSTRAINT `fk_qty_enum` FOREIGN KEY (`qty_enum`) REFERENCES `quantity_enum`(`qty_enum`)
	) ENGINE=InnoDB DEFAULT CHARSET=latin1;
    
    CREATE TABLE IF NOT EXISTS `workout` (
        `name` VARCHAR(50),
        `calories_per_hour` REAL NOT NULL,
        PRIMARY KEY (`name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
    
    CREATE TABLE IF NOT EXISTS `user` (
        `email` VARCHAR(50),
		`username` VARCHAR(50) NOT NULL,
		`password` VARCHAR(500) NOT NULL,
        `weight` REAL NOT NULL,
        `role_enum` VARCHAR(7) NOT NULL,
        PRIMARY KEY (`email`),
        CONSTRAINT `fk_role` FOREIGN KEY(`role_enum`) REFERENCES `role_enum`(`role`)
    )ENGINE=InnoDB DEFAULT CHARSET=latin1;
    
    CREATE TABLE IF NOT EXISTS `food_tracker` (
		`id` INT AUTO_INCREMENT,
        `date` DATE NOT NULL,
        `food_name` VARCHAR(50) NOT NULL,
        `amount` REAL NOT NULL,
        `email` VARCHAR(50) NOT NULL,
        PRIMARY KEY (`id`),
        CONSTRAINT `fk_food` FOREIGN KEY (`food_name`) REFERENCES `food`(`name`),
        CONSTRAINT `fk_email_food` FOREIGN KEY (`email`) REFERENCES `user`(`email`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
    
    CREATE TABLE IF NOT EXISTS `workout_tracker` (
		`id` INT AUTO_INCREMENT,
        `date` DATE NOT NULL,
        `workout_name` VARCHAR(50) NOT NULL,
        `duration` REAL NOT NULL,
        `email` VARCHAR(50) NOT NULL,
        PRIMARY KEY (`id`),
        CONSTRAINT `fk_workout` FOREIGN KEY (`workout_name`) REFERENCES `workout`(`name`),
        CONSTRAINT `fk_email_workout` FOREIGN KEY (`email`) REFERENCES `user`(`email`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
