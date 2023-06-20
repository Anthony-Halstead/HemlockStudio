import React, { useRef, useState, useEffect } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faCartShopping, faCircleUser, faRightFromBracket } from '@fortawesome/free-solid-svg-icons'
import '../../css/reusables/dropdown.css'
import { UseDetectOutsideClick } from './UseDetectOutsideClick';
import { useNavigate } from 'react-router';
import { Link } from 'react-router-dom';
import axios from 'axios';

function DropdownMenu(props) {
  const dropdownRef = useRef(null);
  const [isActive, setIsActive] = UseDetectOutsideClick(dropdownRef, false);
  const [cartItemCount, setCartItemCount] = useState(0)
  const navigate = useNavigate();

  const onClick = () => setIsActive(!isActive);

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

  useEffect(() => {
    quantityProductsInCart();
  }, []);

  const quantityProductsInCart = () => {
    let jwtToken = localStorage.getItem("token");
    axios
      .get("http://3.16.219.108:8080 /cart/findItemsInCart", {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        setCartItemCount(response.data.length);
      })
      .catch((error) => {
        console.error('Error fetching cart items', error);
      });
  }


  return (
    <div className="menu-container">
      <FontAwesomeIcon onClick={onClick} className="menu-trigger" icon={faBars} />

      <nav ref={dropdownRef} className={`menu ${isActive ? 'active' : 'inactive'}`}>
        <ul>
          {props.user.id ? (
            <>
              <li> 
               <Link to="/Cart">
                <FontAwesomeIcon icon={faCartShopping}/> 
                Cart 
                {cartItemCount > 0 && <span className='cart-count'>{cartItemCount}</span>}
               </Link>
              </li>
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