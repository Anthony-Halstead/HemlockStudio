/**
 * @module Home
 */

import React, { useState } from "react";

/**
 * Styles for the Home page.
 * @see {@link ../../css/pages/home.css|Home CSS}
 */
import "../../css/pages/home.css";

/**
 * Reusable Accordion component.
 * @see {@link module:Accordion|Accordion}
 */
import Accordion from "../reusables/Accordion";

/**
 * Story sub-component.
 * @see {@link module:Story|Story}
 */
import Story from "./Story";

/**
 * World sub-component.
 * @see {@link module:World|World}
 */
import World from "./World";

/**
 * Gameplay sub-component.
 * @see {@link module:Gameplay|Gameplay}
 */
import Gameplay from "./Gameplay";

/**
 * Characters sub-component.
 * @see {@link module:Characters|Characters}
 */
import Characters from "./Characters";

/**
 * Background video for the Home page.
 * @constant {string}
 */
import videoBG from "../../video/Trailer_Pilot_04.mp4";

/**
 * The Home component displays the main content for the Home page.
 * 
 * It consists of an accordion layout containing several sections (Story, Characters, World, Gameplay) that can be toggled open and closed.
 * Each section of the accordion displays content specific to that section, using various sub-components.
 * The page also features a looping background video.
 * 
 * @function
 * @returns {JSX.Element} The main content for the Home page.
 */
function Home() {
  const [activeAccordion, setActiveAccordion] = useState(null);
  
  /**
   * Toggles the accordion open/close based on its ID.
   * If the accordion with the provided ID is already open, it will be closed.
   * Otherwise, the accordion will be opened, and any other open accordion will be closed.
   * 
   * @param {number} id - The ID of the accordion to be toggled.
   */
  const toggleAccordion = (id) => {
    setActiveAccordion((prevId) => (prevId === id ? null : id));
  };

  return (
    <div className="main">
      <video className='video' src={videoBG} autoPlay loop muted/>
      <div className="accordion ">
      <Accordion
          id={1}
          title="STORY"
          content={<Story />}
          isOpen={activeAccordion === 1}
          toggleAccordion={toggleAccordion}
        />
        <Accordion
          id={2}
          title="CHARACTERS"
          content={<Characters />}
          isOpen={activeAccordion === 2}
          toggleAccordion={toggleAccordion}
        />
        <Accordion
          id={3}
          title="WORLD"
          content={<World />}
          isOpen={activeAccordion === 3}
          toggleAccordion={toggleAccordion}
        />
        <Accordion
          id={4}
          title="GAMEPLAY"
          content={<Gameplay />}
          isOpen={activeAccordion === 4}
          toggleAccordion={toggleAccordion}
        />
      </div>
    </div>
  );
}

export default Home;
