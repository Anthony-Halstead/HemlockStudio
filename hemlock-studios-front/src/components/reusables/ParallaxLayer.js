/**
 * @module ParallaxLayer
 */

import React, { useState, useEffect } from 'react';
import { useSpring, animated } from '@react-spring/web';

/**
 * ParallaxLayer is a functional component that adds a parallax effect to an image layer as the user scrolls.
 * 
 * @param {Object} props - Properties passed to the component.
 * @param {Object} [props.sticky] - An object containing 'start' and 'end' properties which define when the image should be 'stuck' in viewport as the user scrolls.
 * @param {number} props.speed - A multiplier affecting the parallax speed.
 * @param {number} props.offset - Offset of the image from the top of its container in percentages (0 to 1).
 * @param {string} props.src - Source of the image to be used in the parallax layer.
 * @param {string} props.alt - Alternative text description of the image.
 * @param {string} [props.horizontalAlignment='50%'] - Horizontal alignment of the image, default is centered.
 * @param {number} [props.scaleX=1] - Scale factor in the X-axis for the image.
 * @param {number} [props.scaleY=1] - Scale factor in the Y-axis for the image.
 * @param {number} [props.maxOffset=1] - Maximum vertical offset the layer can move.
 * @returns {JSX.Element} The ParallaxLayer component with parallax effect for the given image.
 */


const ParallaxLayer = ({
  sticky,
  speed,
  offset,
  src,
  alt,
  horizontalAlignment = '50%',
  scaleX = 1,
  scaleY = 1,
  maxOffset = 1,
}) => {

  /** 
   * State variable to store the current Y scroll position.
   * @type {number}
   */
  const [scrollY, setScrollY] = useState(0);

   /**
   * useEffect hook to attach an event listener to the window's scroll event.
   * The listener updates the scrollY state with the current Y scroll position.
   * The listener is cleaned up when the component is unmounted.
   */
  useEffect(() => {
     /**
     * Event listener callback function that sets the current Y scroll position to scrollY state.
     */
    const handleScroll = () => setScrollY(window.pageYOffset);
    window.addEventListener('scroll', handleScroll);

    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);

   /** 
   * Boolean that checks if the current Y scroll position is between the sticky's start and end thresholds.
   * If true, the parallax layer becomes "sticky".
   * @type {boolean}
   */
  const isSticky =
    sticky && scrollY >= sticky.start * window.innerHeight && scrollY <= sticky.end * window.innerHeight;

     /**
   * Calculates the Y translation value for the parallax effect.
   * The value is bound between 0 and maxOffset times the window's inner height.
   * @type {number}
   */
  const translateY = Math.min(Math.max(scrollY * speed, 0), maxOffset * window.innerHeight);

    /** 
   * Generates spring properties for parallax effect using react-spring.
   * @type {Object}
   */
  const parallaxProps = useSpring({
    transform: isSticky
      ? `translateY(${sticky.start * window.innerHeight}px)`
      : `translateY(${translateY}px)`,
  });

  return (
    <animated.div
      style={{
        ...parallaxProps,
        position: isSticky ? 'fixed' : 'absolute',
        top: isSticky ? 0 : `${offset * 100}%`,
        left: horizontalAlignment,
      }}
    >
      <animated.img
        src={src}
        alt={alt}
        style={{
          transform: parallaxProps.transform.to((t) => `${t} translateX(-50%)`),
          scaleX: scaleX,
          scaleY: scaleY,
        }}
      />
    </animated.div>
  );
};

export default ParallaxLayer;