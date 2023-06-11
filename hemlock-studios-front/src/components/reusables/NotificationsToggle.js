import React from 'react';
import axios from 'axios';
import '../../css/reusables/notificationstoggle.css';

function NotificationsToggle(props) {
  const handleToggle = () => {
    axios
      .put('http://localhost:8080/user/toggleNotification')
      .then(response => {
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
        <input type="checkbox" onChange={handleToggle} />
        <span className="toggle-slider"></span>
      </label>
      <span className="toggle-label">Notifications</span>
    </div>
  );
}

export default NotificationsToggle;