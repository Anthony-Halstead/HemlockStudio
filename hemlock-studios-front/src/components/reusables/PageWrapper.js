/**
 * @module PageWrapper
 */

import React from 'react';
import Header from './Header';

/**
 * PageWrapper is a functional component wrapping the main content of a page with a Header component.
 * 
 * @param {Object} props - Properties passed to the component.
 * @param {Object} props.user - The current user object.
 * @param {function} props.setUser - Function to set the user state in a parent component.
 * @param {function} props.setUpdateUser - Function to trigger an update in the parent component/user state.
 * @returns {JSX.Element} The PageWrapper component rendering the Header and any children elements.
 */
function PageWrapper(props) {
  return (
    <div>
      <Header user={props.user} setUser={props.setUser} setUpdateUser={props.setUpdateUser}/>
      <div>{props.children}</div> 
    </div>
  )
}

export default PageWrapper;
