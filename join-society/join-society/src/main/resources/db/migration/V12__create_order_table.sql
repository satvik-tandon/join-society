CREATE TABLE IF NOT EXISTS `order` (
    `id` VARCHAR(40) NOT NULL PRIMARY KEY,
    `customer_id` BIGINT NOT NULL,
    `billing_address_id` BIGINT NOT NULL,
    `shipping_address_id` BIGINT NOT NULL,
    `order_status` ENUM('PROCESSING', 'PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED') NOT NULL,
    `order_subtotal` FLOAT NOT NULL,
    `tax_charges` FLOAT NOT NULL,
    `shipping_fee` FLOAT NOT NULL DEFAULT 0.0,
    `total_amount` FLOAT NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT NOW(),
    `updated_at` DATETIME NOT NULL DEFAULT NOW(),
    CONSTRAINT `fk__order__customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
    CONSTRAINT `fk__order__billing_address` FOREIGN KEY (`billing_address_id`) REFERENCES `address` (`id`),
    CONSTRAINT `fk__order__shipping_address` FOREIGN KEY (`shipping_address_id`) REFERENCES `address` (`id`)
);

CREATE TABLE IF NOT EXISTS `order_entry` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `order_id` VARCHAR(40) NOT NULL,
    `product_id` BIGINT NOT NULL,
    `quantity` INT NOT NULL,
    `price` FLOAT NOT NULL,
    CONSTRAINT `fk__order_entry__order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
    CONSTRAINT `fk__order_entry__product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
    CONSTRAINT `uniq__order__product` UNIQUE (`order_id`, `product_id`)
);
