import React from 'react'

import Draggable from 'react-draggable'
import AdminPanel from '../reusables/AdminPanel'
function About(props) {
  if(props.user.roles.includes('ADMIN')){
    return (
      <div >
           <div className='draggable-wrapper'>
        <Draggable

     defaultPosition={{x: 0, y: 0}}
     bounds={{ top: 0, left: 0, right: 1100, bottom: 900 }}
        >
          <div >
            <AdminPanel/>
          </div>
        </Draggable>
        </div>
      </div> 
    )
  }
  else{
    return <div>About</div>
  }
}

export default About