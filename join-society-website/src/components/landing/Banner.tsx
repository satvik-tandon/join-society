import { HttpStatusCode } from 'axios';
import { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { get } from '../../axios/api';
import { CATCHPHRASE_LINE_ONE, CATCHPHRASE_LINE_THREE, CATCHPHRASE_LINE_TWO } from '../../constants/constants';
import { GET_GENDERS } from '../../constants/endpoints';
import { setGenders } from '../../features/gender/genderSlice';
import { useAppSelector } from '../../hooks';
import { store } from '../../store';

const Banner = () => {
  const { genders } = useAppSelector((state) => state.gender);

  const getGenders = async () => {
    const response = await get({ endpoint: GET_GENDERS });
    if (response?.status === HttpStatusCode.Ok) {
      store.dispatch(setGenders(response.data));
    }
  };

  useEffect(() => {
    getGenders();
  }, []);

  return (
    <div className="banner w-full flex flex-col justify-evenly items-center max-sm:h-[550px] max-sm:gap-2">
      <h2 className="text-white text-center text-6xl font-bold tracking-[1.86px] leading-[60px] max-sm:text-4xl max-[400px]:text-3xl">
        {CATCHPHRASE_LINE_ONE}<br/>
        {CATCHPHRASE_LINE_TWO}<br/>
        {CATCHPHRASE_LINE_THREE}<br/>
      </h2>
      <div className="flex justify-center items-center gap-3 pb-10 max-[400px]:flex-col max-[400px]:gap-1 w-[420px] max-sm:w-[350px] max-[400px]:w-[300px]">
        {genders &&
          genders.map((gender) => (
            <Link
              key={gender.id}
              to={`/shop/${gender.id}`}
              className="bg-white text-black text-center text-xl border border-[rgba(0, 0, 0, 0.40)] font-normal tracking-[0.6px] leading-[72px] w-full h-12 flex items-center justify-center"
            >
              {gender.name}
            </Link>
          ))}
      </div>
    </div>
  );
};

export default Banner;
