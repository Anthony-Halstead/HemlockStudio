/**
 * Carroussel module.
 * 
 * This module provides the Carroussel component, a customizable 3D carousel.
 * 
 * @module Carroussel
 */

/**
 * Carousel import from the react-spring-3d-carousel library.
 * Used to provide 3D carousel functionality.
 * 
 * @see {@link https://www.npmjs.com/package/react-spring-3d-carousel|react-spring-3d-carousel NPM}
 * @external Carousel
 */
import Carousel from "react-spring-3d-carousel";

/**
 * React's default import. Required to define and use React components.
 * 
 * @see {@link https://reactjs.org/|React Official Documentation}
 * @external React
 */
import { useState, useEffect } from "react";

/**
 * Imports spring configuration from the react-spring library.
 * 
 * @see {@link https://www.react-spring.io/|react-spring Official Documentation}
 * @external react-spring
 */
import { config } from "react-spring";

/**
 * Carroussel Component.
 * 
 * Displays a 3D carousel of items. The carousel's appearance and functionality 
 * can be customized through props.
 * 
 * @param {Object} props - Component properties.
 * @param {Object[]} props.cards - An array of card objects to be displayed in the carousel. Each card object should have attributes required by the `Carousel` component.
 * @param {number} props.offset - The offset radius for the carousel, determines how much the carousel is "curved".
 * @param {boolean} props.showArrows - Determines if the carousel's navigation arrows should be shown.
 * @param {string} props.width - CSS width property for the carousel.
 * @param {string} props.height - CSS height property for the carousel.
 * @param {string} props.margin - CSS margin property for the carousel.
 * @returns {React.Element} Rendered Carroussel component.
 */
function Carroussel(props) {
  /**
   * Constructs a new array of card objects with an added `onClick` property.
   * The `onClick` property allows each card to update the `goToSlide` state 
   * when clicked.
   */
  const table = props.cards.map((element, index) => {
    return { ...element, onClick: () => setGoToSlide(index) };
  });

/**
 * offsetRadius - Contains a stateful value and a function to update it.
 * @type {Array} 
 * @property {number} offsetRadius[0] - Represents the offset radius for the carousel, determining how "curved" the carousel appears. Initialized to 2.
 * @property {function} offsetRadius[1] - Function to update the state of `offsetRadius`.
 */
const [offsetRadius, setOffsetRadius] = useState(2);


const [showArrows, setShowArrows] = useState(false);


const [goToSlide, setGoToSlide] = useState(null);


const [cards] = useState(table);


useEffect(() => {
    setOffsetRadius(props.offset);
    setShowArrows(props.showArrows);
}, [props.offset, props.showArrows]);

  return (
    <div
      style={{ width: props.width, height: props.height, margin: props.margin }}
    >
      <Carousel
        slides={cards}
        goToSlide={goToSlide}
        offsetRadius={offsetRadius}
        showNavigation={showArrows}
        animationConfig={config.gentle}
      />
    </div>
  );
}
export default Carroussel