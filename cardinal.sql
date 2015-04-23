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

CREATE PROCEDURE `getPasswordFromEmail` (IN p_email VARCHAR(100))
BEGIN
	SELECT `Password` FROM `cardinal`.`accounts` WHERE `Email`=p_email;
END$$

CREATE PROCEDURE `createSession`(IN p_email VARCHAR(100), IN p_client_token VARCHAR(36), IN p_access_token VARCHAR(36))
BEGIN
	DECLARE p_userId int;
    
    SELECT id INTO p_userId FROM accounts WHERE email=p_email;

	DELETE FROM accountsessions WHERE UserId=p_userId;
    INSERT INTO accountsessions (UserId, ClientToken, AccessToken) VALUES (p_userId, p_client_token, p_access_token);
END$$

CREATE PROCEDURE `authenticateSession` (IN p_client_token VARCHAR(36), IN p_access_token VARCHAR(36))
BEGIN
	DECLARE recent BOOLEAN DEFAULT 0;
	SELECT COUNT(*) INTO recent FROM accountsessions WHERE ClientToken=p_client_token AND AccessToken=p_access_token;
    SELECT recent;
END$$

CREATE PROCEDURE `sessionIsRecent` (IN p_access_token VARCHAR(36))
BEGIN
	DECLARE recent BOOLEAN DEFAULT 0;
	SELECT COUNT(*) INTO recent FROM accountsessions WHERE AccessToken=p_access_token;
    SELECT recent;
END$$

CREATE PROCEDURE `refreshSession`(IN `p_new_access_token` VARCHAR(36), IN `p_access_token` VARCHAR(36), IN `p_client_token` VARCHAR(36))
BEGIN
	UPDATE accountsessions SET AccessToken=p_new_access_token WHERE AccessToken=p_access_token AND ClientToken=p_client_token;
END$$

CREATE PROCEDURE `isValidClientToken` (IN p_client_token VARCHAR(36))
BEGIN
	DECLARE isValid BOOLEAN DEFAULT 0;
	SELECT COUNT(*) INTO isValid FROM accountsessions WHERE ClientToken=p_client_token;
    SELECT isValid;
END$$

CREATE PROCEDURE `invalidateSessionByEmail` (IN p_email VARCHAR(100))
BEGIN
	DECLARE p_userId int;
    
    SELECT id INTO p_userId FROM accounts WHERE email=p_email;

	DELETE FROM accountsessions WHERE UserId=p_userId;
END$$

CREATE PROCEDURE `invalidateSessionByPair` (IN `p_access_token` VARCHAR(36), IN `p_client_token` VARCHAR(36))
BEGIN
	DELETE FROM accountsessions WHERE AccessToken=p_access_token AND ClientToken=p_client_token;
END$$



DELIMITER ;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;