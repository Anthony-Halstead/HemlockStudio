/**
 * @module Accordion
 */

/**
 * React is the base dependency for building React components.
 * @see {@link https://reactjs.org/|React}
 */
import React from "react";

/**
 * `react-spring` is a spring-physics based animation library that should cover most of your UI related animation needs.
 * @see {@link https://www.react-spring.io/|react-spring}
 */
import { useSpring, animated } from "react-spring";

/**
 * The Accordion component displays collapsible content panels for presenting 
 * information in a limited amount of space.
 * 
 * @function
 * @param {Object} props - Properties passed to the component.
 * @param {number} props.id - Unique identifier for the accordion section.
 * @param {string} props.title - Title displayed on the accordion header.
 * @param {JSX.Element} props.content - Content displayed when the accordion is open.
 * @param {boolean} props.isOpen - Indicates if the accordion is currently open or closed.
 * @param {function} props.toggleAccordion - Function to toggle the accordion open or closed based on its id.
 * @returns {JSX.Element} The rendered accordion section.
 */
function Accordion({ id, title, content, isOpen, toggleAccordion }) {
  
  /**
   * Styles for the accordion title, depending on whether it is open or not.
   * @type {Object}
   */
  const styles = {
    accordionTitle: {
      color: isOpen ? "#000000" : "#fff",
    },
  };

  /**
   * Animation properties for the accordion's open/close transition.
   * @type {Object}
   */
  const openAnimation = useSpring({
    from: { opacity: "0", maxHeight: "40px" },
    to: {
      opacity: "1",
      maxHeight: isOpen ? `850px` : "40px",
    },
    config: { duration: "300" },
  });

  /**
   * Handles the toggling of the accordion open/close state.
   * @function
   */
  const handleToggle = () => {
    toggleAccordion(id);
  };


  return (
    <animated.div className="accordion-item" style={openAnimation}>
      <div className="accordion-header" onClick={handleToggle}>
        <h4 style={styles.accordionTitle}>{title}</h4>
      </div>
      {title === "WORLD" ? (
        <div className="accordion-content">{content}</div>
      ) : (
        <div className="accordion-content" onClick={handleToggle}>
          {content}
        </div>
      )}
    </animated.div>
  );
}

export default Accordion;
