import React, { createContext, useState } from 'react';

export const ToastContext = createContext();

export const ToastProvider = ({ children }) => {
    const [message, setMessage] = useState('');

    return (
        <ToastContext.Provider value={{ message, setMessage }}>
            {children}
        </ToastContext.Provider>
    );
};