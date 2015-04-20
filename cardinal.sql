-- =============================================
-- Copyright (c) 2015 Celant Interactive Ltd. All rights reserved.
-- =============================================

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- ---------------------------
-- Drop database and users
-- ---------------------------
DROP DATABASE IF EXISTS `cardinal`;
DROP USER 'cardinalreader';
DROP USER 'cardinaladmin';
DROP USER 'cardinalexecutor';


-- ---------------------------
-- Create database and users
-- ---------------------------
CREATE SCHEMA `cardinal` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `cardinal`;

CREATE USER 'cardinalreader' IDENTIFIED BY 'cardinalreader';
GRANT SELECT ON TABLE `cardinal`.* TO 'cardinalreader';

CREATE USER 'cardinaladmin' IDENTIFIED BY '75&muJHXiPQVBkI%';
GRANT SELECT,INSERT,UPDATE,DELETE,EXECUTE ON TABLE `cardinal`.* TO 'cardinaladmin';

CREATE USER 'cardinalexecutor' IDENTIFIED BY 'oKwE7NRO7wF4xKz';
GRANT EXECUTE ON `cardinal`.* TO 'cardinalexecutor';


-- ---------------------------
-- Create tables
-- ---------------------------
CREATE TABLE `accounts` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(100) NOT NULL,
  `DisplayName` varchar(45) NOT NULL,
  `Password` varchar(60) NOT NULL,
  `CreationDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cardinal`.`accountsessions` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NULL,
  `ClientToken` VARCHAR(36) NULL,
  `AccessToken` VARCHAR(36) NULL,
  PRIMARY KEY (`Id`));



-- --------------------------
-- Create stored procedures
-- --------------------------
DELIMITER $$

DROP procedure IF EXISTS `getPasswordFromEmail`$$
USE `cardinal`$$
CREATE PROCEDURE `getPasswordFromEmail` (IN p_email VARCHAR(100))
BEGIN

SELECT `Password` FROM `cardinal`.`accounts` WHERE `Email`=p_email;

END$$



DELIMITER ;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;