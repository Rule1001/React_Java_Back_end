
CREATE TABLE `property` (
  `property_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `property_type` VARCHAR(255) NOT NULL,
  `bedrooms` INT(10) NOT NULL,
  `location` VARCHAR(255),
  PRIMARY KEY (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;