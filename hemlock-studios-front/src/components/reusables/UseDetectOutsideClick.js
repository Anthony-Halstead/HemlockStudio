import { useState, useEffect } from 'react';

function UseDetectOutsideClick(el, initialState) {
    const [isActive, setIsActive] = useState(initialState);
  
    useEffect(() => {
      const pageClickEvent = (e) => {
        // If the active element exists and is clicked outside of
        if (el.current !== null && !el.current.contains(e.target)) {
          setIsActive(false);
        }
      };
  
      // If the item is active (ie open) then listen for clicks
      if (isActive) {
        window.addEventListener('mousedown', pageClickEvent);
      }
  
      return () => {
        window.removeEventListener('mousedown', pageClickEvent);
      }
  
    }, [isActive, el]);
  
    return ([isActive, setIsActive]);
  }
  
  export { UseDetectOutsideClick };