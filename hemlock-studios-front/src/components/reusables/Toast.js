import React, { useEffect, useState, useContext } from 'react';
import { ToastContext } from './ToastContext';

import '../../css/reusables/toast.css'; 

function Toast() {
    const { message, setMessage } = useContext(ToastContext);

    useEffect(() => {
        if (message !== '') {
            const timer = setTimeout(() => {
                setMessage('');
            }, 3000);  // Set the delay here. 3000 is equivalent to 3 seconds.

            // Cleanup function to clear the timer when the component unmounts or updates
            return () => {
                clearTimeout(timer);
            };
        }
    }, [message, setMessage]);  // Rerun the effect when `message` changes

    return (
        message && (
            <div className="toast">
                {message}
            </div>
        )
    );
}
export default Toast;