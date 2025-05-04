import { HttpStatusCode } from 'axios';
import React, { useEffect, useState } from 'react';
import toast from "react-hot-toast";
import { useParams } from 'react-router-dom';
import { get } from '../axios/api';
import {
  Button,
  Dropdown,
  QuantityInput,
  StandardSelectInput,
  WithNumberInputWrapper,
  WithSelectInputWrapper,
} from '../components';
import { NO_IMG_PLACEHOLDER } from '../constants/constants';
import { GET_PRODUCT_BY_ID } from '../constants/endpoints';
import { getAvailableSizesForProduct, isProductInStock } from '../utils/productUtils';
import { useAppDispatch } from '../hooks';
import { addProductToTheCart } from '../features/cart/cartSlice';

const Product = () => {
  const dispatch = useAppDispatch();

  // Path params
  const params = useParams<{ productId: string }>();

  // State to maintain the fetched product
  const [product, setProduct] = useState<ProductDTO | null>(null);

  // States to maintain the details related to the product
  const [sizes, setSizes] = useState<SizeDTO[]>([]);
  const [sizeId, setSizeId] = useState<number>(0);
  const [colorId, setColorId] = useState<number>(0);
  const [quantity, setQuantity] = useState<number>(1);
  const [maxQuantity, setMaxQuantity] = useState<number>(1);
  const [image, setImage] = useState<string>(NO_IMG_PLACEHOLDER);

  // Define input control elements
  const SelectInputUpgrade = WithSelectInputWrapper(StandardSelectInput);
  const QuantityInputUpgrade = WithNumberInputWrapper(QuantityInput);

  useEffect(() => {
    const getProductById = async () => {
      const productId = params.productId as string;
      const response = await get({ endpoint: GET_PRODUCT_BY_ID.replace(':id', productId) });
      if (response?.status === HttpStatusCode.Ok) {
        setProduct(response.data);
      }
    };

    getProductById();
  }, [params.productId]);

  useEffect(() => {
    if (product !== null) {
      if (product.productDetailDTO.length > 0 && product.productDetailDTO[0].imageUrl) {
        setImage(product.productDetailDTO[0].imageUrl);
        setColorId(product.productDetailDTO[0].color.id);
        setSizeId(product.productDetailDTO[0].size.id);
      }
    }
  }, [product]);

  useEffect(() => {
    const updateImageBasedOnColor = () => {
      const productDetail = product?.productDetailDTO.find((detail) => colorId === detail.color.id);
      if (productDetail && productDetail) {
        setImage(productDetail.imageUrl);
      } else {
        setImage(NO_IMG_PLACEHOLDER);
      }
    };

    const updateSizesBasedOnColor = () => {
      if (colorId === 0 && product) {
        setSizes(product.productCategoryDTO.sizes);
      } else {
        const sizes = getAvailableSizesForProduct(product, colorId);
        setSizes(sizes);
        if (sizes.length > 0) {
          const firstAvailableSize = sizes.find((size) => !size.unavailable);
          setSizeId(firstAvailableSize?.id || 0);
        }
      }
    };

    updateImageBasedOnColor();
    updateSizesBasedOnColor();
  }, [colorId]);

  useEffect(() => {
    const updateMaxQuantity = () => {
      if (product) {
        const matchingProductDetail = product.productDetailDTO.find(
          (detail) => detail.size.id === sizeId && detail.color.id === colorId
        );
        if (matchingProductDetail) {
          setMaxQuantity(matchingProductDetail.inventoryCount);
        }
      }
    };

    updateMaxQuantity();
  }, [sizeId, colorId]);

  const handleAddToCart = () => {
    const errorMessage = "Error while adding product to the cart.";
    
    if (sizeId === 0 || colorId === 0) {
      toast.error(errorMessage);
      return;
    }

    if (product) {
      const productDetail = product.productDetailDTO.find(
        (detail) => detail.size.id === sizeId && detail.color.id === colorId
      );

      if (!productDetail) {
        toast.error(errorMessage)
        return;
      }

      const productInCart: ProductInCart = {
        productId: product.productId,
        productSummary: product.productSummary,
        productDetailId: productDetail.id,
        size: sizes.find((size) => sizeId === size.id)?.name,
        color: product.availableColors.find((color) => colorId === color.id)?.name,
        image: image,
        inventoryCount: productDetail.inventoryCount,
        inStock: isProductInStock(quantity, productDetail.inventoryCount),
        quantity: quantity,
        pricePerQuantity: product.discountedPrice
      }

      dispatch(addProductToTheCart(productInCart));
      toast.success("Added product to cart");
    }
  };

  return (
    <div className="max-w-screen-2xl mx-auto px-5 max-[400px]:px-3">
      <div className="grid grid-cols-3 gap-x-8 max-lg:grid-cols-1">
        <div className="lg:col-span-2">
          <img src={image} alt={product?.productSummary} />
        </div>
        <div className="w-full flex flex-col gap-5 mt-9">
          <div className="flex flex-col gap-2">
            <h1 className="text-4xl">{product?.productSummary}</h1>
            <div className="flex justify-between items-center">
              <p className="text-base text-secondaryBrown">{product?.productCategoryDTO.name}</p>
              <p className="text-base font-bold">${product?.discountedPrice}</p>
            </div>
          </div>
          <div className="flex flex-col gap-2">
            <SelectInputUpgrade
              selectList={sizes.map((size) => ({ id: size.id, value: size.name, disabled: size.unavailable }))}
              value={sizeId.toString()}
              onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setSizeId(() => Number(e.target.value))}
            />
            <SelectInputUpgrade
              selectList={product?.availableColors.map((color) => ({ id: color.id, value: color.name }))}
              value={colorId.toString()}
              onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setColorId(() => Number(e.target.value))}
            />

            <QuantityInputUpgrade
              min={1}
              max={maxQuantity}
              value={quantity}
              onChange={(e: React.ChangeEvent<HTMLInputElement>) => setQuantity(() => parseInt(e.target.value))}
            />
          </div>
          <div className="flex flex-col gap-3">
            <Button mode="brown" text="Add to cart" onClick={handleAddToCart} />
          </div>
          <div>
            <Dropdown openByDefault dropdownTitle="Description">
              {product?.productDescription}
            </Dropdown>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Product;
