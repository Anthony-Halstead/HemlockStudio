import React from 'react';
import '../../css/pages/header.css';
import DropdownMenu from "./DropdownMenu";


function Header({props}) {

    return (
        <div>
            <div className="header">
                <div>
                    <a href="/" className='logo-container'> <img className='logo' src="https://i.imgur.com/dTD6ivm.png" /></a>
                </div>
                <div >
                    <a className=' header-link'href="/" >GAME</a>
                </div>
                <div >
                    <a className=' header-link' href="/store" >SHOP</a>
                </div>
                <div >
                    <a className=' header-link' href="/News" >NEWS </a>
                </div>
                <div >
                    <a className=' header-link' href="/about" >ABOUT</a>
                </div>
                <div className='icon-dimensions'>
                    <div className=' header-link-icon'><DropdownMenu props={props}/></div>
                </div>
            </div>
        </div>
    )
}

export default Header;