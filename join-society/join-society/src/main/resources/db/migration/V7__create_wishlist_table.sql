CREATE TABLE IF NOT EXISTS `wishlist` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer_id` BIGINT NOT NULL,
    CONSTRAINT `fk__wishlist__customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
    CONSTRAINT `uniq__wishlist__customer` UNIQUE (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `wishlist_products` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `wishlist_id` BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    CONSTRAINT `fk__wishlist_products__wishlist` FOREIGN KEY (`wishlist_id`) REFERENCES `wishlist` (`id`),
    CONSTRAINT `fk__wishlist_products__product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
    CONSTRAINT `uniq__wishlist__product` UNIQUE (`wishlist_id`, `product_id`)
);
