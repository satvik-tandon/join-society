CREATE TABLE IF NOT EXISTS `address` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `line1` VARCHAR(128) NOT NULL,
    `line2` VARCHAR(128),
    `postal_code` VARCHAR(10) NOT NULL,
    `is_default` BOOLEAN NOT NULL DEFAULT 0,
    `city_id` INT NOT NULL,
    `customer_id` BIGINT NOT NULL,
    CONSTRAINT `fk__address__customer` FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`),
    CONSTRAINT `fk__address__city` FOREIGN KEY (`city_id`) REFERENCES `city`(`id`)
);
