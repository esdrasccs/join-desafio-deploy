import React, { useEffect } from 'react';

interface PopoverProps {
  message: string;
  onClose: () => void;
}

const Popover: React.FC<PopoverProps> = ({ message, onClose }) => {
  useEffect(() => {
    const timer = setTimeout(onClose, 2000);
    return () => clearTimeout(timer);
  }, [onClose]);
  return (
    <div className="popover">
      <div className="popover-content">
        <p>{message}</p>        
      </div>
    </div>
  );
};

export default Popover;