ALTER TABLE `customer`
ADD COLUMN `phone_number` VARCHAR(15) NOT NULL,
ADD CONSTRAINT `uniq__customer_phone_number` UNIQUE (`phone_number`);
