/**
 * @module SignUp
 */

import '../../css/pages/signinsignup.css';
import '../../css/reusables/positions.css';

/**
 * Axios is a popular, promise-based HTTP client that sports an easy-to-use API and can be used in both the browser and Node.js.
 * @see {@link https://axios-http.com/docs/intro|Axios}
 */
import axios from 'axios';

/**
 * A hook providing navigation functions for React-Router.
 * @see {@link https://reactrouter.com/docs/en/v6/api#usenavigate|useNavigate}
 */
import { useNavigate } from 'react-router';
import { useState } from 'react';

/**
 * A reusable SnowMask component for visual effects.
 * @see {@link module:SnowMask|SnowMask}
 */
import SnowMask from '../reusables/SnowMask';

/**
 * DOMPurify is a DOM-only, super-fast, uber-tolerant XSS sanitizer for HTML, MathML and SVG.
 * @see {@link https://github.com/cure53/DOMPurify|DOMPurify}
 */
import DOMPurify from 'dompurify';

/**
 * The SignUp component facilitates the registration of a new user, providing input validation 
 * and form submission to a defined backend endpoint.
 * 
 * @function
 * @param {Object} props - Properties passed to the component.
 * @param {Object} props.user - User data object for the signup form.
 * @param {function} props.setUser - Function to update the user data.
 * @returns {JSX.Element} The rendered sign-up form.
 */
function SignUp(props) {
    const navigator = useNavigate();
    const [isValid, setIsValid] = useState(false);
    const [errors, setErrors] = useState({});

    /**
     * Validates user input based on predefined rules for email, username, and password.
     * 
     * @function
     * @param {Object} user - User data to validate.
     * @returns {boolean} - Returns true if the user data is valid, otherwise returns false.
     */
    const validate = (user) => {
        // Validation logic here ...

        setErrors(errors);
        return Object.keys(errors).length === 0;
    }

    /**
     * Handle changes to the sign-up form input fields.
     * 
     * @function
     * @param {Event} event - DOM event from the input field.
     */
    const signUpChangeHandler = (event) => {
        // Change handler logic here ...
    };

    /**
     * Handles the sign-up form submission.
     * 
     * @function
     */
    const signUpSubmitHandler = () => {
        if (validate(props.user)) {
            axios.post(`${process.env.REACT_APP_API_URL}/auth/register`, props.user)
                .then(() => {
                    navigator("/SignIn");
                })
                .catch((e) => {
                });
        }
    };

    return (
        <div className='content'>
            <SnowMask />
            <div className='sign-up-sign-in-box'>
                <h1>Sign-Up</h1>
                <div className='input-container'>
                    <input className='input-field' value={props.user.email} name='email' type='email' onChange={signUpChangeHandler} placeholder='EMAIL'></input>
                    <div className='invalid-input'>{errors.email}</div>
                </div>
                <div className='input-container'>
                    <input className='input-field' value={props.user.username} name='username' type='username' onChange={signUpChangeHandler} placeholder='USERNAME'></input>
                    <div className='invalid-input'>{errors.username}</div>
                </div>
                <div className='input-container'>
                    <input className='input-field' value={props.user.password} name='password' type='password' onChange={signUpChangeHandler} placeholder='PASSWORD'></input>
                    <div className='invalid-input'>{errors.password}</div>
                </div>
                <div >
                    <button className='submit-button' onClick={signUpSubmitHandler}>SUBMIT</button>
                </div>
                <div>already have an account? <a href="/SignIn">Click here</a></div>
            </div>
            <div className='logo-box'>              
        </div>
        </div>
    )
}
export default SignUp
