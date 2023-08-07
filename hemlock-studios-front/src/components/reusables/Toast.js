import React, { useEffect, useState, useContext } from 'react';
import { ToastContext } from './ToastContext';
import '../../css/reusables/toast.css'; 

function Toast() {
    const { message, setMessage } = useContext(ToastContext);

    useEffect(() => {
        if (message !== '') {
            const timer = setTimeout(() => {
                setMessage('');
            }, 3000);  

            
            return () => {
                clearTimeout(timer);
            };
        }
    }, [message, setMessage]);  

    return (
        message && (
            <div className="toast">
                {message}
            </div>
        )
    );
}
export default Toast;