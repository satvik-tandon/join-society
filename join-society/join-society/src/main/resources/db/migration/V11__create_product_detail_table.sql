CREATE TABLE IF NOT EXISTS `size` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(8) NOT NULL
);

CREATE TABLE `product_category_size_xref` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product_category_id` INT NOT NULL,
    `size_id` INT not null,
    CONSTRAINT `fk__product_category_size_xref__product_category` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`id`),
    CONSTRAINT `fk__product_category_size_xref__size` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`),
    CONSTRAINT `uniq__product_category__size` UNIQUE (`product_category_id`, `size_id`)
);

CREATE TABLE IF NOT EXISTS `color` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(64) NOT NULL,
    `hex_code` CHAR(7) NOT NULL
);

CREATE TABLE IF NOT EXISTS `product_detail` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product_id` BIGINT NOT NULL,
    `size_id` INT NOT NULL,
    `color_id` INT NOT NULL,
    `image_url` VARCHAR(256),
    `inventory_count` INT DEFAULT 0,
    CONSTRAINT `fk__product_detail__product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
    CONSTRAINT `fk__product_detail__size` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`),
    CONSTRAINT `fk__product_detail__color` FOREIGN KEY (`color_id`) REFERENCES `color` (`id`),
    CONSTRAINT `uniq__product__size__color` UNIQUE (`product_id`, `size_id`, `color_id`)
);

ALTER TABLE `product`
DROP COLUMN `inventory_count`;
