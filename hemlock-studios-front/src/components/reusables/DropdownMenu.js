import React,{useRef} from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faBars} from '@fortawesome/free-solid-svg-icons'
import '../../css/reusables/dropdown.css'
import { UseDetectOutsideClick } from './UseDetectOutsideClick';
import { useNavigate } from 'react-router';
import { Link } from 'react-router-dom';

function DropdownMenu(props) {
  console.log("DropDownMenu:", props.user)
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
                <li><Link to="/Cart">Cart</Link></li>
                <li><Link to="/Account">Account</Link></li>
                <li><Link to="/" onClick={handleSignOut}>Sign Out</Link></li>
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