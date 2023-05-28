import React, { useState } from "react";
import "../../css/pages/home.css";
import Accordion from "../reusables/Accordion";
import { useSpring, animated } from "react-spring";
import Story from "./Story";
import World from "./World";
import Gameplay from "./Gameplay";
import Characters from "./Characters";
import videoBG from "../../video/Unitylogo.mp4";

function Home() {
  const [activeAccordion, setActiveAccordion] = useState(null);


  const toggleAccordion = (id) => {
    setActiveAccordion((prevId) => (prevId === id ? null : id));
  };

  const titleAnimation = useSpring({
    from: {
      transform: "translateY(-30px)",
    },
    to: [{ transform: "translateY(15px)" }],
    config: { mass: 3, tension: 500, friction: 25 },
  });
  return (
    <div className="main">
      <video className='video' src={videoBG} autoPlay loop muted/>
     {/*<animated.h1 style={titleAnimation}>STUFF</animated.h1>*/}
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
