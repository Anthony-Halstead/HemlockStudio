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

/**
 * showArrows - Contains a stateful value and a function to update it.
 * @type {Array} 
 * @property {boolean} showArrows[0] - Indicates if navigation arrows should be displayed on the carousel. Initialized to `false`.
 * @property {function} showArrows[1] - Function to update the state of `showArrows`.
 */
const [showArrows, setShowArrows] = useState(false);

/**
 * goToSlide - Contains a stateful value and a function to update it.
 * @type {Array} 
 * @property {?number} goToSlide[0] - Represents the index of the slide that the carousel should navigate to. `null` indicates no specific navigation request.
 * @property {function} goToSlide[1] - Function to update the state of `goToSlide`.
 */
const [goToSlide, setGoToSlide] = useState(null);

/**
 * cards - Contains a stateful value but lacks a function to update it, as it's initialized and remains constant throughout the component's lifecycle.
 * @type {Array} 
 * @property {Object[]} cards[0] - An array of card objects to be displayed in the carousel, each containing necessary attributes and an additional `onClick` function.
 */
const [cards] = useState(table);

/**
 * Effect hook that updates `offsetRadius` and `showArrows` states based on prop changes.
 * This effect ensures that whenever the `offset` or `showArrows` props change, 
 * the internal state of the component is also updated to reflect these changes.
 * 
 * @listens props.offset - Listens for changes in the `offset` prop.
 * @listens props.showArrows - Listens for changes in the `showArrows` prop.
 */
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