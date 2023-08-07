import React from 'react';
import NotificationsToggle from '../reusables/NotificationsToggle';
import '../../css/pages/account.css'


function Account(props) {
  return (
    <div className='account-content'>
      <div className='account-container'>
      <h1>My Account</h1>
      <h2>Notifications</h2>
      <div>
      <NotificationsToggle setUpdateUser={props.setUpdateUser}/>
      </div>
      </div>
    </div>
  );
}

export default Account;