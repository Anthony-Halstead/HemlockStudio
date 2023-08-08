/**
 * Characters component displays a series of parallax layers with various 
 * images and effects representing different characters and their backgrounds.
 * @module Characters
 * @requires react
 * @requires ../../css/pages/home.css
 * @requires ../../images/SwampTrees.png
 * @requires ../../images/VikingMountains.png
 * @requires ../../images/VikingBlackWoods.png
 * @requires ../../images/VikingWoods.png
 * @requires ../../images/VikingSitting.png
 * @requires ../../images/SnowyTree.png
 * @requires ../../images/NewSwampLandscape.png
 * @requires ../../images/Crystals.png
 * @requires ../../images/ElfNoCrystal.png
 * @requires ../../images/SwampTreeOne.png
 * @requires ../../images/SwampLogOne.png
 * @requires react-spring/parallax
 * @requires ../../css/reusables/dialogbox.css
 * @requires ../../css/reusables/positions.css
 * @requires ../reusables/SnowMask
 * @requires ../reusables/FireflyMask
 * @function
 * @returns {JSX.Element} A series of parallax layers containing images of characters and backgrounds.
 */
import React from 'react';
import '../../css/pages/home.css';
import BackgroundSwamp from '../../images/SwampTrees.png';
import BackgroundSnowMountains from '../../images/VikingMountains.png';
import VikingBlackTrees from '../../images/VikingBlackWoods.png';
import VikingTrees from '../../images/VikingWoods.png';
import Viking from '../../images/VikingSitting.png';
import SnowTree from '../../images/SnowyTree.png'
import Landscape from '../../images/NewSwampLandscape.png';
import Crystals from '../../images/Crystals.png';
import Elf from '../../images/ElfNoCrystal.png';
import TreeOne from '../../images/SwampTreeOne.png'
import LogOne from '../../images/SwampLogOne.png'
/**
 * `@react-spring/parallax` module.
 * 
 * This module provides components and utilities to create smooth and efficient parallax effects in a React application.
 * It's built on top of the `react-spring` animation library.
 * 
 * @see {@link https://www.react-spring.io/docs/hooks/parallax|@react-spring/parallax Official Documentation}
 * @module @react-spring/parallax
 */

/**
 * `Parallax` Component from `@react-spring/parallax`.
 * 
 * The `Parallax` component acts as a container for the parallax effect.
 * It allows for declaring and managing different parallax layers and orchestrating the overall parallax effect.
 * 
 * @example
 * ```jsx
 * <Parallax pages={3}>
 *   // ... ParallaxLayer components go here
 * </Parallax>
 * ```
 * 
 * @see {@link https://www.react-spring.io/docs/hooks/parallax|Parallax Documentation}
 * @memberof module:@react-spring/parallax
 * @external Parallax
 */
import { Parallax } from '@react-spring/parallax';

/**
 * `ParallaxLayer` Component from `@react-spring/parallax`.
 * 
 * The `ParallaxLayer` component represents an individual layer within the parallax effect. 
 * By adjusting the offset, speed, and other properties, you can create intricate parallax animations.
 * 
 * @example
 * ```jsx
 * <ParallaxLayer offset={1} speed={0.5}>
 *   // ... Your content for this layer
 * </ParallaxLayer>
 * ```
 * 
 * @see {@link https://www.react-spring.io/docs/hooks/parallax|ParallaxLayer Documentation}
 * @memberof module:@react-spring/parallax
 * @external ParallaxLayer
 */
import { ParallaxLayer } from '@react-spring/parallax';
import '../../css/reusables/dialogbox.css'
import '../../css/reusables/positions.css'
import SnowMask from '../reusables/SnowMask'
import FireflyMask from '../reusables/FireflyMask'

function Characters() {
    return (
        <div className="characters-container">
            <div className="parallax-container">    
                <Parallax pages={10} >
                    <ParallaxLayer offset={1.13} speed={.2} className='center'>            
                        <img src={BackgroundSwamp} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={1.3} speed={.1}>
                    <FireflyMask />             
                        <img src={Landscape} />                 
                    </ParallaxLayer>              
                    <ParallaxLayer offset={1.2} speed={1} className='center' factor={1}>
                        <img src={Crystals} />                    
                    </ParallaxLayer>
                    <ParallaxLayer offset={1.2} speed={-.1} className='center'>
                        <img src={Elf} style={{ transform: 'scale(1)' }} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={1.3} speed={0} style={{ position: 'absolute' }}>
                        <div >                   
                            <img src={TreeOne} style={{ position: 'absolute', left: '-250px', transform: 'scale(3)' }} />
                        </div>
                    </ParallaxLayer>
                    <ParallaxLayer offset={1.3} speed={0} style={{ position: 'absolute' }}>
                        <img src={LogOne} style={{ position: 'absolute', right: '-500px', transform: 'scale(3)' }} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={2} speed={.1} className='center'>
                        <img src={BackgroundSnowMountains} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={2.5} speed={.1} className='center'>
                        <img src={VikingBlackTrees} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={2.1} speed={.1} className='center'>
                        <img src={VikingTrees} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={2.64} speed={-.1} className='center'>
                        <img src={Viking} style={{ transform: 'scale(1.2)' }} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={2.2} speed={0} style={{ position: 'absolute' }}>
                    <SnowMask />                          
                            <img src={SnowTree} style={{ position: 'absolute', left: '-550px' }} />                                       
                    </ParallaxLayer>
                    <ParallaxLayer offset={2.1} speed={0} style={{ position: 'absolute' }}>
                        <img src={SnowTree} style={{ position: 'absolute', right: '-700px' }} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={1.7} speed={0} className='center'>
                        <div className='dialogue-box'>vuhdbc oashdbc bdf lkjbrlkvjbalksjb</div>
                    </ParallaxLayer>
                </Parallax>
            </div>
        </div>
    );
}
export default Characters