import React from 'react'
import jwt_decode from "jwt-decode";
import '../../css/pages/signinsignup.css'
import '../../css/reusables/positions.css'
import axios from 'axios';
import { useNavigate } from 'react-router';
import EmberMask from '../reusables/EmberMask'

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
            
            console.log("navigated to the home page")
            navigator("/")

          })
          .catch((e) => {
              console.log(e)
          });
  };

    return (
        <div className='sign-in-content'>
            <EmberMask />
            <div className='sign-up-sign-in-box'>
                <h1>Sign-In</h1>
                <div className='input-container'>             
                    <input className='input-field'  value={props.user.username} name='username' type='username' onChange={signInChangeHandler} placeholder='USERNAME'></input>
                </div>
                <div className='input-container'>
                    <input className='input-field' value={props.user.password} name='password' type='password' onChange={signInChangeHandler} placeholder='PASSWORD'></input>
                </div>
                <div >
                    <button className= 'submit-button'onClick={signInSubmitHandler}>SUBMIT</button>
                </div>
                <div>Don't have an account? <a href="/SignUp">Click here</a></div>
            </div>
            <div className='logo-box'>              
        </div>
        </div>
    )
}

export default SignIn