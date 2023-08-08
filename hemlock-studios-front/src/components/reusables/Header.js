/**
 * @module Header
 * @description This module provides the Header component used for site navigation.
 */

import React from 'react';
import '../../css/pages/header.css';
import DropdownMenu from "./DropdownMenu";
import { Link } from 'react-router-dom';

/**
 * Header component that renders the navigation bar of the site.
 * It includes links for the main pages and a dropdown menu for user-related actions.
 *
 * @function
 * @param {Object} props - Properties passed down to the Header component.
 * @param {Object} props.user - Object containing user details.
 * @param {Function} props.setUser - Function to set the user state.
 * @param {Function} props.setUpdateUser - Function to trigger user updates.
 * @returns {React.ReactNode} - The rendered Header component.
 *
 * @example
 * <Header user={user} setUser={setUser} setUpdateUser={setUpdateUser} />
 */
function Header(props) {
    return (
        <div>
            <div className="header">
                <div>
                    <a href="/" className='logo-container'> <img className='logo' src="https://i.imgur.com/dTD6ivm.png" alt="Site Logo" /></a>
                </div>
                <div>
                <Link className=' header-link' to="/">GAME</Link>
                </div>
                <div>
                <Link className=' header-link' to="/news">NEWS</Link>
                </div>
                <div>
                <Link className=' header-link' to="/about">ABOUT</Link>
                </div>
                <div className='icon-dimensions'>
                    <div className=' header-link-icon'>  
                    <DropdownMenu  user={props.user} setUser={props.setUser} setUpdateUser={props.setUpdateUser} />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Header;
