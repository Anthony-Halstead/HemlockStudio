/**
 * SignIn module.
 * 
 * This module provides the SignIn component, which offers users the ability to log in 
 * to the application. It uses JWT for authentication and connects to an external 
 * API for user authentication.
 * 
 * @module SignIn
 */

/**
 * Decodes JSON Web Tokens.
 * 
 * @see {@link https://www.npmjs.com/package/jwt-decode|jwt-decode NPM Documentation}
 * @external jwt_decode
 */
import jwt_decode from "jwt-decode";

// Additional CSS styles for the SignIn component.
import '../../css/pages/signinsignup.css';
import '../../css/reusables/positions.css';

/**
 * Axios is a promise-based HTTP client for the browser and Node.js.
 * 
 * @see {@link https://github.com/axios/axios|Axios GitHub Documentation}
 * @external axios
 */
import axios from 'axios';

/**
 * React Router's `useNavigate` hook, which provides navigation methods.
 * 
 * @see {@link https://reactrouter.com/docs/en/v6/api#usenavigate|useNavigate Documentation}
 * @external useNavigate
 */
import { useNavigate } from 'react-router';

/**
 * EmberMask component.
 * This component is presumed to provide some sort of visual mask effect.
 * More details would be provided in EmberMask's own documentation.
 * 
 * @see module:EmberMask
 */
import EmberMask from '../reusables/EmberMask';

/**
 * DOMPurify sanitizes HTML and prevents XSS attacks.
 * 
 * @see {@link https://github.com/cure53/DOMPurify|DOMPurify GitHub Documentation}
 * @external DOMPurify
 */
import DOMPurify from 'dompurify';

/**
 * SignIn Component.
 * 
 * Provides a user interface and methods for signing into the application.
 * 
 * @param {Object} props - Properties passed to the component.
 * @param {Object} props.user - The user's details.
 * @param {Function} props.setUser - Setter function for updating user's details.
 * 
 * @returns {React.Element} Rendered SignIn component.
 */
function SignIn(props) {
    /**
     * Hook providing navigation methods.
     * 
     * @type {Function}
     */
    const navigator = useNavigate();

    /**
     * Handler for change events on the sign-in input fields.
     * 
     * This function captures the input name and value, sanitizes the value
     * using DOMPurify, then updates the user object with the sanitized value.
     * 
     * @param {Event} event - The triggered event.
     */
    const signInChangeHandler = (event) => {
        const name = event.target.name;
        const value = DOMPurify.sanitize(event.target.value);
        const tempUser = { ...props.user };
        tempUser[name] = value;
        props.setUser(tempUser);
    };
    
  /**
     * Handler for the sign-in form submission.
     * 
     * This function sends a POST request to the authentication API with the user's details.
     * If authentication is successful, it decodes the returned JWT, updates the user object
     * with the details from the decoded token, and then navigates to the home page.
     * 
     */
    const signInSubmitHandler = () => {
      axios.post(`${process.env.REACT_APP_API_URL}/auth/login`, props.user)
          .then((response) => {
              localStorage.setItem("token", response.data.jwt);
              const decodedToken = jwt_decode(response.data.jwt);
              const updatedUser = {
                  id: decodedToken.userId,
                  username: decodedToken.sub,
                  email: decodedToken.email,
                  roles: decodedToken.roles
              };
  
              props.setUser(updatedUser);
            
            navigator("/")

          })
          .catch((e) => {
             
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
