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
import { Parallax, ParallaxLayer } from '@react-spring/parallax'
import '../../css/reusables/dialogbox.css'
import '../../css/reusables/positions.css'


function Characters() {


    return (
        <div className="characters-container">
            <div className="parallax-container">
                <Parallax pages={10} >
                    <ParallaxLayer offset={1.13} speed={.2} className='center'>
                        <img src={BackgroundSwamp} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={1.3} speed={.1}>
                        <img src={Landscape} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={1.2} speed={1} className='center' factor={1}>
                        <img src={Crystals} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={1.2} speed={-.1} className='center'>
                        <img src={Elf} style={{ transform: 'scale(1)' }} />
                    </ParallaxLayer>
                    <ParallaxLayer offset={1.3} speed={0} style={{ position: 'absolute' }}>
                        <img src={TreeOne} style={{ position: 'absolute', left: '-250px', transform: 'scale(3)' }} />
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