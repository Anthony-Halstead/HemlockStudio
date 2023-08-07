import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../../css/reusables/notificationstoggle.css';

function NotificationsToggle(props) {
  const [notificationStatus, setNotificationStatus] = useState(false);

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