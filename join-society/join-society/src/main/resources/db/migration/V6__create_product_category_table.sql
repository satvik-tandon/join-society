CREATE TABLE IF NOT EXISTS `product_category` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(64) NOT NULL,
    `description` TEXT,
    `parent_category_id` INT
);

ALTER TABLE `product_category`
ADD CONSTRAINT `fk__product_category__parent_category` FOREIGN KEY (`parent_category_id`) REFERENCES `product_category`(`id`);

CREATE TABLE `product_category_gender_xref` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product_category_id` INT,
    `gender_id` INT,
    CONSTRAINT `fk__product_category_gender_xref__product_category` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`id`),
    CONSTRAINT `fk__product_category_gender_xref__gender` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
    CONSTRAINT `uniq__product_category__gender` UNIQUE (`product_category_id`, `gender_id`)
);

ALTER TABLE `product`
ADD COLUMN `product_category_id` INT NOT NULL,
ADD CONSTRAINT `fk__product__product_category` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`id`);
