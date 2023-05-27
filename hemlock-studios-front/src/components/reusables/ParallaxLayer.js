import React, { useState, useEffect } from 'react';
import { useSpring, animated } from '@react-spring/web';

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
  const [scrollY, setScrollY] = useState(0);

  useEffect(() => {
    const handleScroll = () => setScrollY(window.pageYOffset);
    window.addEventListener('scroll', handleScroll);

    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);

  const isSticky =
    sticky && scrollY >= sticky.start * window.innerHeight && scrollY <= sticky.end * window.innerHeight;

  const translateY = Math.min(Math.max(scrollY * speed, 0), maxOffset * window.innerHeight);

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