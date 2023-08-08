/**
 * @module NotificationsToggle
 */

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../../css/reusables/notificationstoggle.css';

/**
 * NotificationsToggle is a functional component for toggling user's email notification preferences.
 * 
 * @param {Object} props - Properties passed to the component.
 * @param {function} props.setUpdateUser - Function to trigger an update in the parent component/user state.
 * @returns {JSX.Element} The NotificationsToggle component.
 */
function NotificationsToggle(props) {
  /** 
   * @type {boolean} notificationStatus - Holds the current status of the user's email notification preference.
   */
  const [notificationStatus, setNotificationStatus] = useState(false);

  /**
   * useEffect hook to retrieve the current notification status of the user from the backend when the component mounts.
   */
  useEffect(() => {
    let jwtToken = localStorage.getItem('token');
    axios
      .get(`${process.env.REACT_APP_API_URL}/user/getNotificationStatus`, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
      })
      .then(response => {
        setNotificationStatus(response.data);
      })
      .catch(error => {
        console.log('Error retrieving notification status:', error);
      });
  }, [notificationStatus]);

  /**
   * Toggles the user's email notification preference when the toggle is changed.
   */
  const handleToggle = () => {
    let jwtToken = localStorage.getItem('token');
    axios
      .put(`${process.env.REACT_APP_API_URL}/user/toggleNotification`, null, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
      })
      .then(response => {
        setNotificationStatus(response.data);
        props.setUpdateUser({});
      })
      .catch(error => {
        console.log('Could not toggle', error);
      });
  };

  return (
    <div className="notifications-toggle">
      <label className="toggle-switch">
        <input type="checkbox" checked={notificationStatus} onChange={handleToggle} />
        <span className="toggle-slider"></span>
      </label>
      <span className="toggle-label">Recieve Email Notifications</span>
    </div>
  );
}

export default NotificationsToggle;