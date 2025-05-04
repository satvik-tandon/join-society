import { APP_NAME } from "../../constants/constants";

const Footer = () => {
  return (
      <footer className="max-w-screen-2xl mx-auto border-b-8 border-secondaryBrown px-5 max-[400px]:px-3">
        <div className="flex flex-col gap-8 my-20">
          <h2 className="text-xl font-light text-center max-sm:text-3xl">{APP_NAME}&#169; {new Date().getFullYear()}</h2>
        </div>
      </footer>
  );
};
export default Footer;
