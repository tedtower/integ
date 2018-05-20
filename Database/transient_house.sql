-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema transient_house
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema transient_house
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `transient_house` DEFAULT CHARACTER SET latin1 ;
USE `transient_house` ;

-- -----------------------------------------------------
-- Table `transient_house`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transient_house`.`room` (
  `room_no` TINYINT(2) NOT NULL,
  `capacity` TINYINT(2) NOT NULL,
  `price` SMALLINT(3) NOT NULL,
  `room_status` ENUM('occupied','vacant','unavailable') NOT NULL,
  PRIMARY KEY (`room_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `transient_house`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transient_house`.`reservation` (
  `reservation_no` INT(11) NOT NULL AUTO_INCREMENT,
  `applicant_name` VARCHAR(45) NOT NULL,
  `room_no` TINYINT(2) NOT NULL,
  `reserve_date` DATETIME NOT NULL,
  `check_in` DATETIME NOT NULL,
  `check_out` DATETIME NOT NULL,
  `pay_status` ENUM('paid','unpaid') NOT NULL,
  `no_of_lodgers` TINYINT(2) NOT NULL,
  `amount_payable` INT(11) NOT NULL,
  PRIMARY KEY (`reservation_no`),
  INDEX `fk_reservation_room1_idx` (`room_no` ASC))
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `transient_house`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transient_house`.`payment` (
  `payment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `reservation_no` INT(11) NOT NULL,
  `payment_date` DATETIME NOT NULL,
  `amount` INT(11) NOT NULL,
  PRIMARY KEY (`payment_id`),
  INDEX `fk_payment_reservation_idx` (`reservation_no` ASC))
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
