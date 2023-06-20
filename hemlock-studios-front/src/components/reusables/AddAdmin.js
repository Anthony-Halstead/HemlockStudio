import axios from 'axios';
import React, { useContext, useState } from 'react'
import '../../css/pages/addproduct.css';
import { ToastContext } from '../reusables/ToastContext';

function AddAdmin() {
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

    const registerChangeHandler = (event) => {
        const { name, value } = event.target;
        const tempAdmin = { ...admin, [name]: value };
        setAdmin(tempAdmin);
        validate(tempAdmin);
      };


    const registerSubmitHandler = () => {
        if (validate(admin)) {
            let jwtToken = localStorage.getItem('token');
            axios.post("https://LBtest-01-1681136195.us-east-2.elb.amazonaws.com/auth/registerAdmin", admin,
            {
              headers: {
                Authorization: `Bearer ${jwtToken}`,
              },
            }
          )
                .then((response) => {
                    console.log(response.data)
                    handleAddedMessage();
                })
                .catch((e) => {
                    console.log(e);
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