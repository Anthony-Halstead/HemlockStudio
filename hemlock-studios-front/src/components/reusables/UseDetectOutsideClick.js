/**
 * @module UseDetectOutsideClick
 * @description This module provides a custom React hook for detecting clicks outside a given element.
 */

import { useState, useEffect } from 'react';

/**
 * Custom hook that checks if a mouse click event happened outside of the provided element. 
 * The hook returns a state value indicating if the component is active, and a function to set its state.
 *
 * @function
 * @param {React.RefObject} el - A ref object pointing to the element to detect outside clicks for.
 * @param {boolean} initialState - Initial state of the component's active status.
 * @returns {Array} An array containing two values:
 * 1. A boolean indicating the component's active state.
 * 2. A function to set the component's active state.
 * 
 * @example
 * const [isActive, setIsActive] = UseDetectOutsideClick(ref, false);
 */
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
