ALTER TABLE `product`
ADD COLUMN `base_price` FLOAT NOT NULL,
ADD COLUMN `discounted_price` FLOAT;

ALTER TABLE `product`
DROP CONSTRAINT `fk__product__product_price`,
DROP COLUMN `product_price_id`;

DROP TABLE IF EXISTS `product_price`;
