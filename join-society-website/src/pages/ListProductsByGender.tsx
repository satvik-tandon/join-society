import { useParams } from 'react-router-dom';
import { ListProducts } from '../components';
import { useAppSelector } from '../hooks';

const ListProductsByGender = () => {
  // Get gender state
  const { genders } = useAppSelector((state) => state.gender);

  // Get path parameters
  const pathParams = useParams();
  const genderId = Number(pathParams.genderId);

  // Find gender name by id
  const genderName = genders.find((gender) => gender.id === genderId)?.name;

  const bannerTitle = genderName ? `${genderName}'S COLLECTION` : undefined

  return (
    <div className="max-w-screen-2xl mx-auto pt-10">
      <ListProducts bannerTitle={bannerTitle} genderId={genderId}/>
    </div>
  );
};

export default ListProductsByGender;
