interface ISelectElement {
  id: number;
  value: string;
  disabled?: boolean
}

const StandardSelectInput = ({ selectList, ...props }: { selectList: ISelectElement[] }) => {
  return (
    <select className="w-full px-2 py-2 border-black/30 border text-black/70 outline-none" {...props}>
      {selectList &&
        selectList.map((element: ISelectElement) => (
          <option key={element.id} value={element.id} disabled={Boolean(element.disabled)}>
            {element.value}
          </option>
        ))}
    </select>
  );
};

export default StandardSelectInput;
