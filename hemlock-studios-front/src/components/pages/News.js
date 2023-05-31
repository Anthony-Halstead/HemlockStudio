import React from 'react'
import AdminPanel from '../reusables/AdminPanel'
import Draggable from 'react-draggable'
import '../../css/pages/admin.css'

function News(props) {
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
    return <div>News</div>
  }
}

export default News