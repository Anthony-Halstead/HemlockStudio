import React from "react";
import { useSpring, animated } from "react-spring";

function Accordion({ id, title, content, isOpen, toggleAccordion }) {
  const styles = {
    accordionTitle: {
      color: isOpen ? "#000000" : "#fff",
    },
  };

  const openAnimation = useSpring({
    from: { opacity: "0", maxHeight: "40px" },
    to: {
      opacity: "1",
      maxHeight: isOpen ? `850px` : "40px",
    },
    config: { duration: "300" },
  });

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
