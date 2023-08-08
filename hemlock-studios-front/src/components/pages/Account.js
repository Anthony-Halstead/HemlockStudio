/**
 * @module Account
 */

import React from 'react';

/**
 * NotificationsToggle component to handle user's notification settings.
 * @see {@link module:NotificationsToggle|NotificationsToggle}
 */
import NotificationsToggle from '../reusables/NotificationsToggle';

/**
 * Styles for the Account page.
 * @see {@link ../../css/pages/account.css|Account CSS}
 */
import '../../css/pages/account.css';

/**
 * The Account component displays user's account settings, such as the ability to toggle notifications.
 * 
 * @function
 * @param {Object} props - Properties passed to the component.
 * @param {function} props.setUpdateUser - Function to trigger a user update.
 * @returns {JSX.Element} The rendered account content.
 */
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
