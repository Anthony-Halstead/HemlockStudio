import React,{useRef} from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faBars} from '@fortawesome/free-solid-svg-icons'
import '../../css/reusables/dropdown.css'
import { UseDetectOutsideClick } from './UseDetectOutsideClick';
import { useNavigate } from 'react-router';

function DropdownMenu({props}) {
    const dropdownRef = useRef(null);
    const [isActive, setIsActive] = UseDetectOutsideClick(dropdownRef, false);
    const navigate = useNavigate();

    const onClick = () => setIsActive(!isActive);

    const handleSignOut = () => {
      // Clear user state and JWT
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
        <FontAwesomeIcon onClick={onClick} className ="menu-trigger" icon={faBars} />
        
        <nav ref={dropdownRef} className={`menu ${isActive ? 'active' : 'inactive'}`}>    
          <ul>
            {props.user.id ? (
              <>
                <li><a href="/Favorites">Favorites</a></li>
                <li><a href="/Account">Account Settings</a></li>
                <li><a href="/" onClick={handleSignOut}>Sign Out</a></li>
              </>
            ) : (
              <>
                <li><a href="/SignIn">Sign-In</a></li>
                <li><a href="/SignUp">Sign-Up</a></li>
              </>
            )}
          </ul>
        </nav>
      </div>
    );
}

export default DropdownMenu;