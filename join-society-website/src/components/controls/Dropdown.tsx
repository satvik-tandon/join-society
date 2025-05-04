import { useState } from 'react';
import { HiChevronDown, HiChevronUp } from 'react-icons/hi2';

interface DropdownProps {
  dropdownTitle: string;
  openByDefault?: boolean;
  children: React.ReactNode;
}

const Dropdown = (props: DropdownProps) => {
  const [isOpen, setIsOpen] = useState(Boolean(props.openByDefault));
  return (
    <div>
      <div
        className="flex justify-between items-center border-b border-black/30 h-14 cursor-pointer"
        onClick={() => setIsOpen((prev) => !prev)}
      >
        <p className="text-black/95 text-base">{props.dropdownTitle}</p>
        {isOpen ? <HiChevronUp className="text-base" /> : <HiChevronDown className="text-base" />}
      </div>
      {isOpen && (
        <div className="mt-4">
          <p className="text-sm">{props.children}</p>
        </div>
      )}
    </div>
  );
};
export default Dropdown;
