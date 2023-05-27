import React, { useState } from "react";
import { useSpring, animated } from "react-spring";
import '../../css/reusables/image.css'

function Card({ imagen }) {
  const [show, setShown] = useState(false);

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