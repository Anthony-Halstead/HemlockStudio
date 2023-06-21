import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../../css/reusables/notificationstoggle.css';

function NotificationsToggle(props) {
  const [notificationStatus, setNotificationStatus] = useState(false);

  useEffect(() => {
    let jwtToken = localStorage.getItem('token');
    axios
      .get('https://LBtest-01-1681136195.us-east-2.elb.amazonaws.com/user/getNotificationStatus', {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
      })
      .then(response => {
        setNotificationStatus(response.data);
        console.log('Notification status retrieved successfully');
      })
      .catch(error => {
        console.log('Error retrieving notification status:', error);
      });
  }, [notificationStatus]);

  const handleToggle = () => {
    let jwtToken = localStorage.getItem('token');
    axios
      .put('https://LBtest-01-1681136195.us-east-2.elb.amazonaws.com/user/toggleNotification', null, {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
      })
      .then(response => {
        setNotificationStatus(response.data);
        props.setUpdateUser({});
        console.log('Toggle Success');
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