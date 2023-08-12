/**
 * Card module.
 * 
 * This module provides the Card component which represents an animated card
 * that reacts to mouse hover events by slightly scaling its size.
 * 
 * @module Card
 */

/**
 * React's default import. Required to define and use React components.
 * 
 * @see {@link https://reactjs.org/|React Official Documentation}
 * @external React
 */
import React, { useState } from "react";

/**
 * Imports from the react-spring library to handle spring animations.
 * 
 * @see {@link https://www.react-spring.io/|react-spring Official Documentation}
 * @external react-spring
 */
import { useSpring, animated } from "react-spring";

/**
 * Card Component.
 * 
 * Displays an animated card with an image and a caption. The card's size scales 
 * slightly when hovered over, and returns to its normal size when the hover ends.
 * 
 * @param {Object} props - Component properties.
 * @param {string} props.imagen - URL of the image to be displayed on the card.
 * @returns {React.Element} Rendered Card component.
 */

import '../../css/reusables/image.css'

function Card({ imagen }) {
    /**
     * State variable to track whether the card is currently being hovered over.
     */
    const [show, setShown] = useState(false);

    /**
     * Spring properties for the hover animation.
     * The card scales to 1.03 times its size when hovered over, 
     * and scales back to its normal size when the hover ends.
     */
    const props3 = useSpring({
        transform: show ? "scale(1.03)" : "scale(1)"
    });

  return (
    <animated.div
     className='image'
      style={props3}
      onMouseEnter={() => setShown(true)}
      onMouseLeave={() => setShown(false)}
    >
      <img src={imagen} alt="" />
      <p>
    In a world of people killing
      </p>
    </animated.div>
  );
}

export default Card;