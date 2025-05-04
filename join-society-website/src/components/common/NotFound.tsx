interface NotFoundProps {
  title?: string;
  message?: string;
  children: React.ReactElement
}

const NotFound = (props: NotFoundProps) => {
  let title = 'OOPS!';

  if (props.title) {
    title = props.title;
  }

  let message = 'The page you are looking for was not found!';
  if (props.message) {
    message = props.message;
  }

  return (
    <div className="max-w-screen-2xl mx-auto pt-20">
      <h1 className="text-5xl font-light text-center">{title}</h1>
      <p className="text-center mt-5 text-lg">{message}</p>
      {props.children}
    </div>
  );
};

export default NotFound;
