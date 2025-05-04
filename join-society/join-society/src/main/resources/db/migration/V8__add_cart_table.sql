CREATE TABLE IF NOT EXISTS `cart` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer_id` BIGINT NOT NULL,
    `cart_total` FLOAT NOT NULL DEFAULT 0.0,
    `created_at` DATETIME NOT NULL DEFAULT NOW(),
    `updated_at` DATETIME NOT NULL DEFAULT NOW(),
    CONSTRAINT `fk__cart__customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

CREATE TABLE IF NOT EXISTS `cart_entry` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `cart_id` BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    `quantity` INT NOT NULL DEFAULT 1,
    CONSTRAINT `fk__cart_entry__cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
    CONSTRAINT `fk__cart_entry__product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
    CONSTRAINT `uniq__cart_entry__cart__product` UNIQUE (`cart_id`, `product_id`)
);
