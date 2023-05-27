import React from 'react';
import '../../css/pages/header.css';
import '../../css/reusables/dimensions.css';
import DropdownMenu from "./DropdownMenu";


function Header(props) {


    const signOut = () => {
        localStorage.removeItem("token");
        props.setUser({
            id: undefined,
            username: "",
            email: "",
            roles: []
        });
        navigator("/");
    };


    return (

        <div>
            <div className="header">
                <div className='third-width'>
                    <a href="/" className='logo-container'> <img className='logo' src="https://i.imgur.com/dTD6ivm.png" /></a>
                </div>
                <div className='third-width'>
                    <a href="/">
                        <div className=' header-link'>UNTITLED GAME</div>
                    </a>
                </div>
                <div className='third-width'>
                    <a href="/store">
                        <div className=' header-link'>SHOP</div>
                    </a>
                </div>
                <div className='third-width'>
                    <a href="/about">
                        <div className=' header-link'>ABOUT US</div>
                    </a>
                </div>
                <div className='icon-dimensions'>
                    <div className=' header-link-icon'><DropdownMenu /></div>
                </div>
            </div>
        </div>
    )
}

export default Header;