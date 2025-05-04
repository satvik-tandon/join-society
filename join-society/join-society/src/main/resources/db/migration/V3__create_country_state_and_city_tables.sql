CREATE TABLE IF NOT EXISTS `country` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(32) NOT NULL,
    `iso_code` CHAR(2) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS `state` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(32) NOT NULL,
    `country_id` INT,
    CONSTRAINT `fk__state__country` FOREIGN KEY(`country_id`) REFERENCES `country`(`id`)
);

CREATE TABLE IF NOT EXISTS `city` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(32) NOT NULL,
    `state_id` INT,
    CONSTRAINT `fk__city__state` FOREIGN KEY(`state_id`) REFERENCES `state`(`id`)
);
