import React, { useState } from "react";
import "../../css/pages/home.css";
import Accordion from "../reusables/Accordion";
import Story from "./Story";
import World from "./World";
import Gameplay from "./Gameplay";
import Characters from "./Characters";
import videoBG from "../../video/Trailer_Pilot_04.mp4";

function Home() {
  const [activeAccordion, setActiveAccordion] = useState(null);
  
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
