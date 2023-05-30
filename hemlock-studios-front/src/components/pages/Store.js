import React from 'react'
import AdminPanel from '../reusables/AdminPanel'



function Store(props) {
  if(props.user.roles.includes('ADMIN')){
    return (
      <div className='admin-content'>
      <div className='admin-panel-content'>
        <AdminPanel/>
      </div>
      </div>
    )
  }
  else{
    <div>Store</div>
  }
}

export default Store