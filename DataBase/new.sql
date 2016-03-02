-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.26-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for fmd-gp2016
CREATE DATABASE IF NOT EXISTS `fmd-gp2016` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fmd-gp2016`;


-- Dumping structure for table fmd-gp2016.client_to_server_message
CREATE TABLE IF NOT EXISTS `client_to_server_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) NOT NULL,
  `device_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1105BDDB2FCE4BA6` (`user_id`),
  KEY `FK1105BDDB44F2F8C6` (`device_id`),
  CONSTRAINT `FK1105BDDB2FCE4BA6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK1105BDDB44F2F8C6` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table fmd-gp2016.device
CREATE TABLE IF NOT EXISTS `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `content` varchar(10000) DEFAULT NULL,
  `last_active_date` datetime NOT NULL,
  `mac_address` varchar(60) NOT NULL,
  `name` varchar(30) NOT NULL,
  `online` bit(1) NOT NULL,
  `password` varchar(30) NOT NULL,
  `type` bit(1) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB06B1E562FCE4BA6` (`user_id`),
  CONSTRAINT `FKB06B1E562FCE4BA6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table fmd-gp2016.devicelocation
CREATE TABLE IF NOT EXISTS `devicelocation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` varchar(255) NOT NULL,
  `longitude` varchar(255) NOT NULL,
  `takein` datetime DEFAULT NULL,
  `device_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22DB384B44F2F8C6` (`device_id`),
  CONSTRAINT `FK22DB384B44F2F8C6` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table fmd-gp2016.server_to_client_message
CREATE TABLE IF NOT EXISTS `server_to_client_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) NOT NULL,
  `device_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9901C7DB2FCE4BA6` (`user_id`),
  KEY `FK9901C7DB44F2F8C6` (`device_id`),
  CONSTRAINT `FK9901C7DB2FCE4BA6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK9901C7DB44F2F8C6` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table fmd-gp2016.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `email` varchar(60) NOT NULL,
  `mobileno` varchar(30) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
