CREATE TABLE IF NOT EXISTS `customer` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(128) NOT NULL,
    `middle_name` VARCHAR(128),
    `last_name` VARCHAR(128) NOT NULL,
    `email` VARCHAR(256) NOT NULL UNIQUE,
    `password_hash` VARCHAR(256) NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT NOW(),
    `updated_at` DATETIME NOT NULL DEFAULT NOW()
);
