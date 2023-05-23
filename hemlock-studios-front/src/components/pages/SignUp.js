import '../../css/pages/signupsignin.css'
import '../../css/reusables/positions.css'
import axios from 'axios';
import { useNavigate } from 'react-router';
import { useState } from 'react';

function SignUp(props) {
    const navigator = useNavigate()
    const[isValid, setIsValid] = useState(false)
    const[errors,setErrors] = useState({})

    const validate = (user) => {
        const errors = {}
        const emailRegex = /(?:[a-z0-9+!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/i;
        const usernameRegex = /^[A-Za-z][A-Za-z0-9_]{7,29}$/i;
        const passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$/i;

        if (!emailRegex.test(user.email)) {
            errors.email = "Invalid email";
        }
        if (!usernameRegex.test(user.username)) {
            errors.username = "Invalid username";
        }
        if (!passwordRegex.test(user.password)) {
            errors.password = "Invalid password";
        }

        setErrors(errors);
        return Object.keys(errors).length === 0;

    }

    const signUpChangeHandler = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        const tempUser = { ...props.user };
        tempUser[name] = value;
        props.setUser(tempUser);
        validate(tempUser);
    };


    const signUpSubmitHandler = () => {
        if (validate(props.user)) {
            axios.post("http://localhost:8080/auth/register", props.user)
                .then(() => {
                    navigator("/EmailConfirmation");
                })
                .catch((e) => {
                    console.log(e);
                });
        }
    };

    return (
        <div className='signup-signin-content'>
            <div className='sign-up-box'>
                <div>
                    New User? Sign-up Today!
                </div>
                <h1>Sign-Up</h1>
                <div className='flex-row justify-content-left'>
                    EMAIL
                    <input className='input-container' value={props.user.email} name='email' type='email' onChange={signUpChangeHandler} ></input>
                    <div className='flex-row justify-content-left'>{errors.email}</div>
                </div>
                <div className='flex-row justify-content-left'>
                    USERNAME
                    <input className='input-container' value={props.user.username} name='username' type='username' onChange={signUpChangeHandler} ></input>
                    <div className='flex-row justify-content-left'>{errors.username}</div>
                </div>
                <div className='flex-row justify-content-left'>
                    PASSWORD
                    <input className='input-container' value={props.user.password} name='password' type='password' onChange={signUpChangeHandler} ></input>
                    <div className='flex-row justify-content-left'>{errors.password}</div>
                </div>
                <div className='flex-row justify-content-center'>
                    <button onClick={signUpSubmitHandler}>SUBMIT</button>
                </div>
            </div>
        </div>
    )
}
export default SignUp
