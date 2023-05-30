import React, { useEffect } from 'react'
import AdminPanel from '../reusables/AdminPanel'
import '../../css/pages/admin.css'
import '../../css/reusables/adminpanel.css'
function Admin(props) {


  return (
    <div className='admin-content'>
      <div className= 'admin-panel-content'>
      <AdminPanel />
      </div>
      <div>ANALYTICS</div>
    </div>
  )
}

export default Admin