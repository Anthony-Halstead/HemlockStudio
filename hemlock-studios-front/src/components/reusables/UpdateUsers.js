/**
 * @module UpdateUsers
 * @description This module provides functionality for updating users.
 */

import React from 'react';
import Get from './Get';

/**
 * The `UpdateUsers` component serves as a wrapper around the `Get` component to fetch user entities.
 * 
 * @function
 * @returns {React.Element} A React component for displaying or updating users.
 */
function UpdateUsers() {
  return ( 
       <Get entityType="user" />
  )
}

export default UpdateUsers;
