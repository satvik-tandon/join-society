import { Link } from 'react-router-dom';
import { NO_IMG_PLACEHOLDER } from '../../constants/constants';

const ProductItem = ({ item }: { item: ProductItem }) => {
  return (
    <div className="w-[400px] flex flex-col gap-2 justify-center max-md:w-[300px]">
      <Link to={`/product/${item.id}`} className="w-full h-[300px] max-md:h-[200px] overflow-hidden">
        <img src={item.imageUrl || NO_IMG_PLACEHOLDER} alt={item.summary} />
      </Link>
      <Link to={`/product/${item.id}`} className="text-black text-center text-3xl tracking-[1.02px] max-md:text-2xl">
        <h2>{item.summary}</h2>
      </Link>
      <p className="text-secondaryBrown text-lg tracking-wide text-center max-md:text-base">{item.category}</p>
      <p className="text-black text-2xl text-center font-bold max-md:text-xl">${item.price}</p>
      <div className="w-full flex flex-col gap-1">
        <Link
          to={`/product/${item.id}`}
          className="text-white bg-secondaryBrown text-center text-xl font-normal tracking-[0.6px] leading-[72px] w-full h-12 flex items-center justify-center max-md:text-base"
        >
          View Product
        </Link>
      </div>
    </div>
  );
};

export default ProductItem;
