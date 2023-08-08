/**
 * @module DropdownMenu
 */

import React, { useRef } from 'react'

/**
 * Importing FontAwesome icons and their respective components.
 */
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faCircleUser, faRightFromBracket } from '@fortawesome/free-solid-svg-icons'

/**
 * Importing associated CSS styles for the dropdown menu.
 */
import '../../css/reusables/dropdown.css'

/**
 * Hook to detect clicks outside a ref element, used for closing the dropdown.
 */
import { UseDetectOutsideClick } from './UseDetectOutsideClick';

/**
 * `useNavigate` is a hook for programmatic navigation.
 */
import { useNavigate } from 'react-router';

/**
 * React Router's Link component for declarative navigation.
 */
import { Link } from 'react-router-dom';

/**
 * DropdownMenu is a functional component for displaying a user dropdown menu with options based on the user's authentication state.
 * 
 * @param {Object} props - Properties passed to the component.
 * @param {Object} props.user - Object containing user details.
 * @param {number} props.user.id - The ID of the user, used to determine authentication status.
 * @param {function} props.setUser - Function to update the user state in a parent component.
 * @returns {JSX.Element} The DropdownMenu component.
 */
function DropdownMenu(props) {

  /**
   * Reference to the dropdown navigation element. Used with the `UseDetectOutsideClick` hook.
   */
  const dropdownRef = useRef(null);

  /**
   * `isActive` determines whether the dropdown is active or not.
   * `setIsActive` is the function used to toggle the active state.
   */
  const [isActive, setIsActive] = UseDetectOutsideClick(dropdownRef, false);

  /**
   * Hook that returns a navigate function for programmatic navigation.
   */
  const navigate = useNavigate();

  /**
   * Toggles the active state of the dropdown menu.
   */
  const onClick = () => setIsActive(!isActive);

  /**
   * Handles the sign-out functionality. Resets the user state, removes the JWT token from localStorage and redirects to the home page.
   */
  const handleSignOut = () => {
    props.setUser({
      id: undefined,
      username: "",
      email: "",
      roles: [],
    });
    localStorage.removeItem('token');
    navigate('/');
  };


  return (
    <div className="menu-container">
      <FontAwesomeIcon onClick={onClick} className="menu-trigger" icon={faBars} />
      <nav ref={dropdownRef} className={`menu ${isActive ? 'active' : 'inactive'}`}>
        <ul>
          {props.user.id ? (
            <>
              <li>
                <Link to="/Account">
                <FontAwesomeIcon icon={faCircleUser} />
                Account
                </Link>
                </li>
              <li><Link to="/" onClick={handleSignOut}>
              <FontAwesomeIcon icon={faRightFromBracket} />
                Sign Out
                </Link></li>
            </>
          ) : (
            <>
              <li><Link to="/SignIn">Sign-In</Link></li>
              <li><Link to="/SignUp">Sign-Up</Link></li>
            </>
          )}
        </ul>
      </nav>
    </div>
  );
}

export default DropdownMenu;