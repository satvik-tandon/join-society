CREATE TABLE IF NOT EXISTS `product_price` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `base_price` FLOAT NOT NULL,
    `discounted_price` FLOAT DEFAULT 0.0,
    `discount_percent` FLOAT DEFAULT 0.0,
    `created_at` DATETIME NOT NULL DEFAULT NOW(),
    `updated_at` DATETIME NOT NULL DEFAULT NOW()
);

ALTER TABLE `product`
ADD COLUMN `product_price_id` BIGINT NOT NULL,
ADD CONSTRAINT `fk__product__product_price` FOREIGN KEY (`product_price_id`) REFERENCES `product_price` (`id`);
