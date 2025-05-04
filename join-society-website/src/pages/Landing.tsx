import React from 'react';
import { Banner, ListProducts } from '../components';
import { NEW_ARRIVALS } from '../constants/constants';

const Landing = () => {
  return (
    <React.Fragment>
      <Banner />
      <ListProducts bannerTitle={NEW_ARRIVALS} />
    </React.Fragment>
  );
};

export default Landing;
