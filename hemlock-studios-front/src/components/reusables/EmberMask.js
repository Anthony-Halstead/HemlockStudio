import React from 'react'
import Particles from 'react-tsparticles'
import {useCallback} from 'react'
import {loadFull} from 'tsparticles'


function EmberMask() {
    const init = useCallback(async (engine) => {
        await loadFull(engine)
       });
       
       return (
        <Particles options={{ 
            particles:{
              color: {
                value: "#F76806"
              },
              opacity:{
                value:1,
                animation: {
                  enable: true,
                  speed: 1,
                  minimumValue: 0
                },
              },
              number: {
                value: 100
              },
              shape: {
                type: "square"
              },
              size: {
                value: {min:1, max:5}
              },
              move: {
                direction: "top",
                enable: true,
                speed: {min:5, max: 8},
              }
            }
        }} init ={init}/>
        
        )
}

export default EmberMask