import React from 'react'
import '../../css/pages/admin.css'
import Draggable from 'react-draggable'
import AdminPanel from '../reusables/AdminPanel'
function About(props) {
  if(props.user.roles.includes('ADMIN')){
    return (
      <div className='admin-content'>
        <div className='draggable-wrapper'>
        <Draggable

     defaultPosition={{x: 0, y: 0}}
     bounds={{top: -210, left: 0, right: 1000, bottom: 200}}
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