import React from 'react'
import '../../css/reusables/adminpanel.css'

function AdminPanel() {
  return (
      <div className='admin-panel-elements'>
        <div className='admin-panel-header'>
      Admin 
      </div>
      <div className='admin-elements'>
      Analytics
      </div>
      <div className='admin-elements'>
      Manage Users
      </div>
      <div className='admin-elements'>
      Manage Products
      </div>
      <div className='admin-elements'>
      Manage News
      </div>
    </div>
    
  )
}

export default AdminPanel