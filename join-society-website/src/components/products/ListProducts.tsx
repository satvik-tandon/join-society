import ListProductsBanner from './ListProductsBanner';
import ProductGrid from './ProductGrid';
import ProductGridWrapper from './ProductGridWrapper';

interface ListProductsProps {
  bannerTitle?: string;
  genderId?: number;
}

const ListProducts = (props: ListProductsProps) => {
  return (
    <>
      <ListProductsBanner title={props.bannerTitle} />
      <ProductGridWrapper genderId={props.genderId}>
        <ProductGrid />
      </ProductGridWrapper>
    </>
  );
};

export default ListProducts;
