import React from 'react';
import NotificationsToggle from '../reusables/NotificationsToggle';
import AddToCartButton from '../reusables/AddToCartButton';
import Favorites from '../reusables/Favorites';
import CreditCard from '../reusables/CreditCard';
import '../../css/pages/account.css'

function Account(props) {
  return (
    <div className='account-content'>
      <h2>Account Information</h2>
      <CreditCard user={props.user} setUser={props.setUser} setUpdateUser={props.setUpdateUser}/>
      
      <h2>Favorites</h2>
      <Favorites user={props.user} setUser={props.setUser} setUpdateUser={props.setUpdateUser}/>
      <AddToCartButton user={props.user} setUser={props.setUser} setUpdateUser={props.setUpdateUser}/>

      <h2>Notifications</h2>
      <NotificationsToggle setUpdateUser={props.setUpdateUser}/>
      
    </div>
  );
}

export default Account;