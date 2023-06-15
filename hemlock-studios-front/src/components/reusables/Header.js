import React from 'react';
import '../../css/pages/header.css';
import DropdownMenu from "./DropdownMenu";
import { Link } from 'react-router-dom';



function Header(props) {
    console.log("Header:", props.user)
   

    return (
        <div>
            <div className="header">
                <div>
                    <a href="/" className='logo-container'> <img className='logo' src="https://i.imgur.com/dTD6ivm.png" /></a>
                </div>
                <div>
                <Link className=' header-link' to="/">GAME</Link>
                </div>
                <div>
                <Link className=' header-link' to="/store">SHOP</Link>    
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
    )
}

export default Header;