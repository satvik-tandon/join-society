export const getAvailableSizesForProduct = (product: ProductDTO | null, colorId: number) => {
  const sizes: SizeDTO[] = [];

  if (product !== null) {
    product.productCategoryDTO.sizes.forEach((size) => {
      let unavailable = true;
      product.productDetailDTO.forEach((detail) => {
        if (detail.size.id === size.id && detail.color.id === colorId) {
          unavailable = detail.inventoryCount == 0;
        }
      });
      sizes.push({ ...size, unavailable });
    });
  }

  return sizes;
};


export const isProductInStock = (quantity: number, totalInventoryCount: number): boolean => {
  return quantity <= totalInventoryCount;
}

export const calculateShippingFee = (subtotal: number): number => {
  const FREE_SHIPPING_THRESHOLD = 50; // Free shipping for orders above $50
  const FLAT_RATE_SHIPPING = 5;      // Flat shipping fee below threshold

  if (subtotal === 0) {
    return 0; // No shipping for an empty cart
  }
  return subtotal >= FREE_SHIPPING_THRESHOLD ? 0 : FLAT_RATE_SHIPPING;
}

export const calculateTaxFee = (subtotal: number, taxRate: number = 8.25): number => {
  if (subtotal === 0) {
    return 0; // No tax for an empty cart
  }

  return parseFloat((subtotal * (taxRate / 100)).toFixed(2)); // Calculate tax and round to 2 decimals
}
