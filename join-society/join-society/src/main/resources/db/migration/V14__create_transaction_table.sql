CREATE TABLE IF NOT EXISTS `transaction` (
    `id` VARCHAR(40) NOT NULL PRIMARY KEY,
    `order_id` VARCHAR(40) NOT NULL,
    `payment_method_id` BIGINT NOT NULL,
    `transaction_date` DATETIME NOT NULL DEFAULT NOW(),
    `status` ENUM('ON_HOLD', 'APPROVED', 'REJECTED') NOT NULL,
    CONSTRAINT `fk__transaction__order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
    CONSTRAINT `fk__transaction__payment_method` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`)
);