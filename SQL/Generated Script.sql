-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BOOKSTORE
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BOOKSTORE
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BOOKSTORE` DEFAULT CHARACTER SET utf8 ;
USE `BOOKSTORE` ;

-- -----------------------------------------------------
-- Table `BOOKSTORE`.`PUBLISHER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKSTORE`.`PUBLISHER` (
  `PublisherName` VARCHAR(45) NOT NULL,
  `Telephone` VARCHAR(15) NULL,
  `Address` VARCHAR(150) NULL,
  PRIMARY KEY (`PublisherName`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKSTORE`.`BOOK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKSTORE`.`BOOK` (
  `ISBN` INT UNSIGNED NOT NULL,
  `Title` VARCHAR(150) NOT NULL,
  `PublisherName` VARCHAR(45) NOT NULL,
  `PublicationYear` YEAR NULL,
  `Price` INT UNSIGNED NOT NULL,
  `Category` VARCHAR(10) NOT NULL,
  `Threshold` INT UNSIGNED NOT NULL,
  `Copies` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ISBN`),
  UNIQUE INDEX `Title_UNIQUE` (`Title` ASC) VISIBLE,
  INDEX `BOOK_TO_PUBLISHER_idx` (`PublisherName` ASC) INVISIBLE,
  INDEX `CATEGORY_INDEX` USING BTREE (`Category`) VISIBLE,
  CONSTRAINT `BOOK_TO_PUBLISHER`
    FOREIGN KEY (`PublisherName`)
    REFERENCES `BOOKSTORE`.`PUBLISHER` (`PublisherName`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKSTORE`.`BOOK_AUTHOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKSTORE`.`BOOK_AUTHOR` (
  `ISBN` INT UNSIGNED NOT NULL,
  `Fname` VARCHAR(30) NOT NULL,
  `Lname` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ISBN`, `Fname`, `Lname`),
  INDEX `FNAME_INDEX` USING BTREE (`Fname`) VISIBLE,
  CONSTRAINT `AUTHOR_TO_BOOK`
    FOREIGN KEY (`ISBN`)
    REFERENCES `BOOKSTORE`.`BOOK` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKSTORE`.`BOOK_ORDERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKSTORE`.`BOOK_ORDERS` (
  `ISBN` INT UNSIGNED NOT NULL,
  `TotalOrdered` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ISBN`),
  CONSTRAINT `ORDER_TO_BOOK`
    FOREIGN KEY (`ISBN`)
    REFERENCES `BOOKSTORE`.`BOOK` (`ISBN`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BOOKSTORE`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BOOKSTORE`.`USER` (
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(300) NOT NULL,
  `PasswordHash` VARCHAR(45) NOT NULL,
  `Fname` VARCHAR(30) NOT NULL,
  `Lname` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(15) NOT NULL,
  `ShippingAddress` VARCHAR(150) NOT NULL,
  `IsManager` TINYINT NOT NULL,
  PRIMARY KEY (`UserName`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
