/**
 * @module Toast
 * @description This module provides a toast notification component.
 */

import React, { useEffect, useState, useContext } from 'react';

/**
 * Import the Toast context which provides message management for the Toast component.
 * @external ToastContext
 * @see {@link ./ToastContext}
 */
import { ToastContext } from './ToastContext';

/** Import the styles for the toast component. */
import '../../css/reusables/toast.css'; 

/**
 * The Toast component displays a toast notification using the message from ToastContext.
 * The toast automatically disappears after 3 seconds.
 * 
 * @function
 * @returns {React.Element} A toast notification if a message is present.
 */
function Toast() {
    /**
     * Destructure the message and setMessage from the ToastContext.
     * @constant
     * @type {string}
     * @default ''
     */
    const { message, setMessage } = useContext(ToastContext);

    /**
     * useEffect hook to auto-hide the toast message after 3 seconds.
     */
    useEffect(() => {
        if (message !== '') {
            const timer = setTimeout(() => {
                setMessage('');
            }, 3000);  

            /**
             * Cleanup function to clear the timer when the component is unmounted or when the message changes.
             */
            return () => {
                clearTimeout(timer);
            };
        }
    }, [message, setMessage]);  

    return (
        /** Render the toast message if one is present. */
        message && (
            <div className="toast">
                {message}
            </div>
        )
    );
}

export default Toast;
