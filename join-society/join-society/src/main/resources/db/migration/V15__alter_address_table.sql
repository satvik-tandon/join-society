ALTER TABLE `address`
DROP CONSTRAINT `fk__address__city`,
DROP COLUMN `city_id`;

DROP TABLE IF EXISTS `city`;

ALTER TABLE `address`
ADD COLUMN `city` VARCHAR(128) NOT NULL,
ADD COLUMN `state_id` INT NOT NULL,
ADD CONSTRAINT `fk__address__state` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`);
