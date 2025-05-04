ALTER TABLE `product`
ADD COLUMN `gender_id` INT NOT NULL,
ADD CONSTRAINT `fk__product__gender` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`);

DROP TABLE IF EXISTS `product_category_gender_xref`;
