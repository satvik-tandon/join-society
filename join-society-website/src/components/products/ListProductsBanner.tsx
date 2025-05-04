import { PRODUCTS_BANNER_DEFAULT_TITLE } from '../../constants/constants';

interface ListProductsBannerProps {
  title?: string;
}

const ListProductsBanner = (props: ListProductsBannerProps) => {
  let title = props.title || PRODUCTS_BANNER_DEFAULT_TITLE;
  title = title.toUpperCase();

  return (
    <div className="bg-secondaryBrown text-white py-10 flex justify-center items-center mx-5 my-10">
      <h2 className="text-3xl max-sm:text-2xl">{title}</h2>
    </div>
  );
};

export default ListProductsBanner;
