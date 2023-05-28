import React from 'react'
import Particles from 'react-tsparticles'

function FireflyMask() {
    return (
        <Particles id="firefly"   options={{ 
          
            particles: {
                color: {
                    value: "#ffff00"
                },
          
                number: {
                    value: 30
                },
                opacity: {
                    value: 0.5,
                    random: true,
                    animation: {
                        enable: true,
                        speed: 0.5,
                        minimumValue: 0,
                        sync: false
                    }
                },
                size: {
                    value: 3
                },
                move: {
                    enable: true,
                    speed: 1,
                    direction: "none",
                    random: true,
                    straight: false,
                    outModes: {
                        default: "out"
                    },
                    attract: {
                        enable: false,
                        rotateX: 600,
                        rotateY: 1200
                    }
                }
            }
        }} />
    )
}

export default FireflyMask