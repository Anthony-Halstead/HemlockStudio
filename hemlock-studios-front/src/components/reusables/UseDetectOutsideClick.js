import { useState, useEffect } from 'react';

function UseDetectOutsideClick(el, initialState) {
  const [isActive, setIsActive] = useState(initialState);

  useEffect(() => {
    const pageClickEvent = (e) => {
      // If the active element exists and is clicked outside of
      if (el.current !== null && !el.current.contains(e.target)) {
        // Add a small delay before setting isActive to false
        setTimeout(() => {
          setIsActive(false);
        }, 100);
      }
    };

    // If the item is active (ie open) then listen for mouseup events
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