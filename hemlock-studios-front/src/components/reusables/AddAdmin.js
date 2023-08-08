/**
 * AddAdmin module.
 * 
 * This module provides the AddAdmin component, which allows privileged users
 * to add new administrator accounts to the application. The component includes
 * validation for email, username, and password fields.
 * 
 * @module AddAdmin
 */

/**
 * Axios is a promise-based HTTP client for the browser and Node.js.
 * 
 * @see {@link https://github.com/axios/axios|Axios GitHub Documentation}
 * @external axios
 */
import axios from 'axios';

/**
 * React's default import. Required to define and use React components.
 * 
 * @see {@link https://reactjs.org/|React Official Documentation}
 * @external React
 */
import React, { useContext, useState } from 'react';

// CSS styles specific to the AddAdmin component.
import '../../css/pages/addproduct.css';

/**
 * Context for displaying toast notifications.
 * 
 * @see module:ToastContext
 */
import { ToastContext } from '../reusables/ToastContext';

/**
 * DOMPurify sanitizes HTML and prevents XSS attacks.
 * 
 * @see {@link https://github.com/cure53/DOMPurify|DOMPurify GitHub Documentation}
 * @external DOMPurify
 */
import DOMPurify from 'dompurify';

/**
 * AddAdmin Component.
 * 
 * Component that provides an interface for adding a new administrator account.
 * 
 * @returns {React.Element} Rendered AddAdmin component.
 */
function AddAdmin() {
      /**
     * Context function to set messages for toast notifications.
     * 
     * @type {Function}
     */
    const { setMessage } = useContext(ToastContext);

    const handleAddedMessage = () => {
      setMessage('A New Admin Profile Was Added');
  };
  
    const [errors, setErrors] = useState({})
    const [admin, setAdmin] = useState({
        username: "",
        email: "",
        password: "",
    });

     /**
     * Validates the provided administrator account details.
     * 
     * This function checks the email, username, and password of a potential 
     * administrator account using regular expressions to ensure they meet the 
     * required criteria. If any of the checks fail, an error message is set in 
     * the errors state object for the respective field.
     * 
     * @param {Object} admin - The administrator details to validate.
     * @returns {boolean} - True if validation succeeds, false otherwise.
     */
    const validate = (admin) => {
        const errors = {}
        const emailRegex = /(?:[a-z0-9+!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/i;
        const usernameRegex = /^[A-Za-z][A-Za-z0-9_]{7,29}$/i;
        const passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&\-+=()])(?=\S+$).{8,20}$/i;

        if (!emailRegex.test(admin.email)) {
            errors.email = "Invalid email";
        }
        if (!usernameRegex.test(admin.username)) {
            errors.username = "Invalid username";
        }
        if (!passwordRegex.test(admin.password)) {
            errors.password = "Invalid password";
        }

        setErrors(errors);
        return Object.keys(errors).length === 0;

    }

      /**
     * Handles changes on the registration form fields.
     * 
     * Captures the input name and value, sanitizes the value, and updates the admin state.
     * It also calls the validate function to validate the updated admin object.
     * 
     * @param {Event} event - The triggered event from the input field.
     */
    const registerChangeHandler = (event) => {
        const { name, value } = event.target;
        const sanitizedValue = DOMPurify.sanitize(value);
        const tempAdmin = { ...admin, [name]: sanitizedValue };
        setAdmin(tempAdmin);
        validate(tempAdmin);
    };
    

  /**
     * Handles the registration form submission.
     * 
     * Sends a POST request to register a new administrator if the input is validated.
     * If the registration is successful, a toast message is displayed.
     * 
     */
    const registerSubmitHandler = () => {
        if (validate(admin)) {
            let jwtToken = localStorage.getItem('token');
            axios.post(`${process.env.REACT_APP_API_URL}/auth/registerAdmin`, admin,
            {
              headers: {
                Authorization: `Bearer ${jwtToken}`,
              },
            }
          )
                .then((response) => {
                    handleAddedMessage();
                })
                .catch((e) => {
    
                });
        }
    };
    
    return (
        <div className='add-product-content'>
            <div >
                <h1>Add Admin</h1>
                <div className='input-container'>
                    <input className='input-field' value={admin.email} name='email' type='email' onChange={registerChangeHandler} placeholder='EMAIL'></input>
                    <div className='invalid-input'>{errors.email}</div>
                </div>
                <div className='input-container'>
                <input className='input-field' value={admin.username} name='username' type='text' onChange={registerChangeHandler} placeholder='USERNAME'></input>
                    <div className='invalid-input'>{errors.username}</div>
                </div>
                <div className='input-container'>
                    <input className='input-field' value={admin.password} name='password' type='password' onChange={registerChangeHandler} placeholder='PASSWORD'></input>
                    <div className='invalid-input'>{errors.password}</div>
                </div>
                <div >
                    <button className='submit-button' onClick={registerSubmitHandler}>SUBMIT</button>
                </div>
            </div>
        </div>
    )
}
export default AddAdmin