import React from 'react'
import Particles from 'react-tsparticles'
import {useCallback} from 'react'
import {loadFull} from 'tsparticles'

function LeavesMask() {
   
 const init = useCallback(async (engine) => {
    await loadFull(engine)
   });
   
   return (
  <Particles options={{ 
    particles:{
      color: {
        value: "#00ff00"
      },
      number: {
        value: 100
      },
      shape: {
        type: "leaves"
      },
      size: {
        value: {min:1, max:5}
      },
      move: {
        direction: "bottom",
        enable: true,
        speed: {min:2, max: 4},
       
      }
      
    }
  }} init ={init}/>
    )
  }
  


export default LeavesMask