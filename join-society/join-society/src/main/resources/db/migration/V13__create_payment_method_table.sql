CREATE TABLE IF NOT EXISTS `payment_method` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer_id` BIGINT NOT NULL,
    `payment_type` ENUM('CREDIT_CARD', 'DEBIT_CARD') NOT NULL,
    `name_on_card` VARCHAR(128) NOT NULL,
    `card_number` VARCHAR(16) NOT NULL,
    `card_expiry` VARCHAR(8) NOT NULL,
    `is_default` BOOLEAN DEFAULT 0,
    CONSTRAINT `fk__payment_method__customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

