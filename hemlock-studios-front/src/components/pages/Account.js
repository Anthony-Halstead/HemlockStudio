import React from 'react';
import NotificationsToggle from '../reusables/NotificationsToggle';
import '../../css/pages/account.css'
import Favorites from '../reusables/Favorites';
import CreditCard from '../reusables/CreditCard';

function Account(props) {
  return (
    <div className='account-content'>
      <div className='account-container'>
      <h1>My Account</h1>
      <h2>Notifications</h2>
      <div>
      <NotificationsToggle setUpdateUser={props.setUpdateUser}/>
      </div>
      <h2>Credit Card Information</h2>
      <div >
      <CreditCard user={props.user} setUser={props.setUser} setUpdateUser={props.setUpdateUser}/>
      </div>
      <h2>Favorites</h2>
      <Favorites user={props.user} setUser={props.setUser} setUpdateUser={props.setUpdateUser}/>
      </div>
    </div>
  );
}

export default Account;