import React from 'react'
import Particles from 'react-tsparticles'
import {useCallback} from 'react'
import {loadFull} from 'tsparticles'


function SnowMask() {
 
 const init = useCallback(async (engine) => {
  await loadFull(engine)
 });
 
 return (
<Particles id="snow"  options={{ 
   
  particles:{
    color: {
      value: "#ffff"
    },
   
    number: {
      value: 100
    },
    opacity: {
      value: {min: 0.3, max: 1}
    },
    shape: {
      type: "circle"
    },
    size: {
      value: {min:1, max:5}
    },
    move: {
      direction: "bottom",
      enable: true,
      speed: {min:5, max: 8},
      
    }
    
  }
}} init ={init}/>
  )
}

export default SnowMask