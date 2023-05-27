import React,{useRef, useState, useEffect} from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faBars, faCircleUser} from '@fortawesome/free-solid-svg-icons'
import '../../css/reusables/dropdown.css'
import { UseDetectOutsideClick } from './UseDetectOutsideClick';



function DropdownMenu() {
    const dropdownRef = useRef(null);
    const [isActive, setIsActive] = UseDetectOutsideClick(dropdownRef, false);
    const onClick = () => setIsActive(!isActive);

  return (
   <div className="menu-container">
  
        <FontAwesomeIcon onClick={onClick} className ="menu-trigger" icon={faBars} />
   
    <nav ref={dropdownRef} className={`menu ${isActive ? 'active' : 'inactive'}`}>
        <ul>
            <li><a href="/SignIn">Sign-In</a></li>
            <li><a href="/SignUp">Sign-Up</a></li>
            <li><a href="/Favorites">Favorites</a></li>
            <li><a href="/Account">Account Settings</a></li>
        </ul>
    </nav>
   </div>
  );
}

export default DropdownMenu