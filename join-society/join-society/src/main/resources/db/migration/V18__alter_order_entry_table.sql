-- Drop the foreign key constraints first
-- Note that both need to be dropped in order to drop the unique constraint next
ALTER TABLE `order_entry`
DROP CONSTRAINT `fk__order_entry__order`,
DROP CONSTRAINT `fk__order_entry__product`;

-- Now, drop the unique constraint
ALTER TABLE `order_entry`
DROP CONSTRAINT `uniq__order__product`;

-- Drop the product_id column (this will be replaced by product_detail_id)
ALTER TABLE `order_entry`
DROP COLUMN `product_id`;

-- Add the product_detail_id column
ALTER TABLE `order_entry`
ADD COLUMN `product_detail_id` BIGINT NOT NULL;

-- Add the previously dropped foreign key constraint for order_id, a new foreign key constraint for product_detail_id,
-- and a new unique constraint
ALTER TABLE `order_entry`
ADD CONSTRAINT `fk__order_entry__order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
ADD CONSTRAINT `fk__order_entry__product_detail` FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`),
ADD CONSTRAINT `uniq__order__product_detail` UNIQUE (`order_id`, `product_detail_id`);
