import React, { ReactElement, useCallback, useEffect, useState } from 'react';
import { get } from '../../axios/api';
import { GET_PRODUCTS } from '../../constants/endpoints';
import { HttpStatusCode } from 'axios';

interface ProductGridWrapperProps {
  genderId?: number;
  children: ReactElement<{ products: ProductDTO[] }>;
}

interface GetProductsApiParams {
  genderId?: number;
}

const ProductGridWrapper = (props: ProductGridWrapperProps) => {
  const [products, setProducts] = useState<ProductDTO[]>([]);

  // Memoize the function to prevent unnecessary re-renders
  const getProducts = useCallback(async () => {
    const allProducts = [];

    const params: GetProductsApiParams = {};
    if (props.genderId) {
      params.genderId = props.genderId;
    }

    const response = await get({ endpoint: GET_PRODUCTS, params: params });
    if (response?.status === HttpStatusCode.Ok) {
      allProducts.push(...response.data);
    }

    setProducts(allProducts);
  }, [props.genderId]);

  useEffect(() => {
    getProducts();
  }, [getProducts]);

  // Clone the children and pass the products as props to the children
  const childrenWithProps = React.Children.map(props.children, (child) => {
    if (React.isValidElement(child) && products.length > 0) {
      return React.cloneElement(child, { products: products });
    }
    return null;
  });

  return childrenWithProps;
};

export default ProductGridWrapper;
