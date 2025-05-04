const QuantityInput = ({ min, max, ...props }: { min: number, max: number }) => {
  return (
    <input
      type="number"
      min={min}
      max={max}
      {...props}
      className="w-full px-2 py-2 h-10 bg-white outline-none border-black/30 border text-black/70"
      placeholder="Enter product quantity"
    />
  );
};

export default QuantityInput;
