import { useState, useEffect } from 'react';

function UseDetectOutsideClick(el, initialState) {
  const [isActive, setIsActive] = useState(initialState);

  useEffect(() => {
    const pageClickEvent = (e) => {
      if (el.current !== null && !el.current.contains(e.target)) {
        setTimeout(() => {
          setIsActive(false);
        }, 100);
      }
    };

    if (isActive) {
      window.addEventListener('mouseup', pageClickEvent);
    }

    return () => {
      window.removeEventListener('mouseup', pageClickEvent);
    }

  }, [isActive, el]);
  return ([isActive, setIsActive]);
}

export { UseDetectOutsideClick };