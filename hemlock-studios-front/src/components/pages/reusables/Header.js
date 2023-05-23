import React from 'react';
import '../../../css/pages/header.css';
import '../../../css/reusables/dimensions.css';


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

function Header() {
    return (

        <div>
            <div className="header">
                <div className='third-width'>
                    <a href="/" className='logo-container'> <img className='logo' src="https://i.imgur.com/dTD6ivm.png" /></a>
                </div>
                <div className='third-width'>
                    <a href="/store">
                        <div className=' header-link'>STORE</div>
                    </a>                    
                </div>
                <div className='third-width'>
                    <a href="/about">
                        <div className=' header-link'>ABOUT</div>
                    </a>
                </div>
            </div>
        </div>
    )
}

export default Header;