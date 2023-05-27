import React from 'react'
import jwt_decode from "jwt-decode";

import '../../css/reusables/positions.css'
import axios from 'axios';
import { useNavigate } from 'react-router';

function SignIn(props) {


    const navigator = useNavigate()


    const signInChangeHandler = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        const tempUser = { ...props.user };
        tempUser[name] = value;
        props.setUser(tempUser);
    };
    

    const signInSubmitHandler = () => {
      axios.post("http://localhost:8080/auth/login", props.user)
          .then((response) => {

              console.log("response", response.data)
              localStorage.setItem("token", response.data.jwt);
              console.log("You set the token")
              const decodedToken = jwt_decode(response.data.jwt);
              console.log("token decoded")
              const updatedUser = {
                  id: decodedToken.userId,
                  username: decodedToken.sub,
                  email: decodedToken.email,
                  roles: decodedToken.roles
              };
  
              props.setUser(updatedUser);
              navigator("/");
          })
          .catch((e) => {
              console.log(e)
          });
  };

    return (
        <div className='signup-signin-content'>
            <div className='sign-up-box'>
                <div>Already A User?</div>
                <h1>Sign-In</h1>
                <div className='flex-row justify-content-left'>
                    USERNAME
                    <input className='input-container' value={props.user.username} name='username' type='username' onChange={signInChangeHandler} ></input>
                </div>
                <div className='flex-row justify-content-left'>
                    PASSWORD
                    <input className='input-container' value={props.user.password} name='password' type='password' onChange={signInChangeHandler} ></input>
                </div>
                <div className='flex-row justify-content-center'>
                    <button onClick={signInSubmitHandler}>SUBMIT</button>
                </div>
            </div>

        </div>
    )
}

export default SignIn