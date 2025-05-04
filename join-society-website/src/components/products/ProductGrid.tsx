import React from 'react';
import ProductItem from './ProductItem';

// Memoize the component to prevent unnecessary re-renders because of React.cloneElement
const ProductGrid = React.memo(({ products }: { products?: ProductDTO[] }) => {
  return (
    <div
      id="gridTop"
      className="max-w-screen-2xl flex flex-wrap justify-between items-center gap-y-8 mx-auto mt-12 max-xl:justify-start max-xl:gap-5 px-5 max-[400px]:px-3"
    >
      {products &&
        products.map((product: ProductDTO) => (
          <ProductItem
            key={product.productId}
            item={{
              id: product.productId,
              summary: product.productSummary,
              imageUrl: product.productDetailDTO?.length > 0 ? product.productDetailDTO[0].imageUrl : undefined,
              category: product.productCategoryDTO.name,
              price: product.discountedPrice,
            }}
          />
        ))}
    </div>
  );
});

export default ProductGrid;
