import React, { useRef, useState, useEffect } from 'react';
import { useSprings, animated } from '@react-spring/web';
import { useDrag } from 'react-use-gesture';
import clamp from 'lodash.clamp';
import TropicalRuins from "../../images/TropicalRuins.png";
import DwarfStatues from "../../images/DwarfStatues.png";

const images = [
    TropicalRuins,
    DwarfStatues,
    "https://i.pinimg.com/originals/94/f9/55/94f955d545010b4ccfdd9d63e13cc5a0.jpg",
    "https://cdnb.artstation.com/p/users/covers/000/099/217/default/3807537c7501122ce2e7cae56a3ea263.jpg?1452533519",
    "https://wallpaperaccess.com/full/2832177.jpg",
];

function World() {
  const [containerWidth, setContainerWidth] = useState(0);
  const [currentIndex, setCurrentIndex] = useState(0); // Using only for indicator logic
  const containerRef = useRef(null);
  const index = useRef(0); // Re-introducing the index ref for drag logic

  useEffect(() => {
    const handleResize = () => {
      if (containerRef.current) {
        setContainerWidth(containerRef.current.getBoundingClientRect().width);
      }
    };
  
    // Call this function immediately to get the initial width
    handleResize();
  
    window.addEventListener('resize', handleResize);
  
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  const [springs, setSprings] = useSprings(images.length, i => ({
    x: i === 0 ? 0 : containerWidth
}));

useEffect(() => {
  if (containerWidth > 0) {
      setSprings(i => ({
          x: i === index.current ? 0 : containerWidth
      }));
  }
}, [containerWidth]);


  const bind = useDrag(({ movement: [x], direction: [xDir], down, distance, cancel }) => {
    const isTryingToSwipePastBounds = 
        (xDir > 0 && index.current === 0) || 
        (xDir < 0 && index.current === images.length - 1);

    if (isTryingToSwipePastBounds) {
      if (cancel) cancel();
    }

    if (!down && distance > containerWidth / 4) { 
      index.current = clamp(index.current + (xDir > 0 ? -1 : 1), 0, images.length - 1);
      setCurrentIndex(index.current); // Synchronize the state with the ref
    }

    setSprings(i => {
      if (i === index.current) {
        return { x: down ? x : 0 };
      } else if (i === index.current - 1) {
        return { x: down ? x - containerWidth : -containerWidth };
      } else if (i === index.current + 1) {
        return { x: down ? x + containerWidth : containerWidth };
      } else {
        return { x: containerWidth };
      }
    });
  }, { filterTaps: true });

  return (
    <div ref={containerRef} style={{ overflow: 'hidden', width: '98%', height: '85vh', margin: '0 auto', position: 'relative' }}>
      {springs.map(({ x }, i) => (
        <animated.div
          {...bind()}
          key={i}
          style={{
            position: 'absolute',
            top: 0,
            left: 0,
            width: '100%',
            height: '100%',
            backgroundImage: `url(${images[i]})`,
            backgroundSize: 'cover',
            backgroundPosition: 'center',
            touchAction: 'none',
            transform: x.to(x => `translateX(${x}px)`)
          }}
        />
      ))}

      <div style={{
        position: 'absolute',
        bottom: '20px',
        left: '50%',
        transform: 'translateX(-50%)',
        display: 'flex',
        gap: '10px'
      }}>
        {images.map((_, i) => (
          <div key={i} style={{
            width: '10px',
            height: '10px',
            borderRadius: '50%',
            backgroundColor: currentIndex === i ? 'white' : 'rgba(255, 255, 255, 0.5)'
          }}></div>
        ))}
      </div>
    </div>
  );
}

export default World;