/**
 * @module ToastContext
 * @description This module provides a context for managing toast notifications.
 */

import React, { createContext, useState } from 'react';

/**
 * `ToastContext` is the main context for the Toast system.
 * @constant
 * @type {React.Context}
 */
export const ToastContext = createContext();

/**
 * The `ToastProvider` component wraps child components, providing them access
 * to the toast message state and its updater function.
 * 
 * @function
 * @param {Object} props - The component props.
 * @param {React.ReactNode} props.children - Child components that will have access to the ToastContext.
 * @returns {React.Element} A React context provider element.
 */
export const ToastProvider = ({ children }) => {
    /**
     * State and its updater for managing the current toast message.
     * @constant
     * @type {string}
     * @default ''
     */
    const [message, setMessage] = useState('');

    return (
        <ToastContext.Provider value={{ message, setMessage }}>
            {children}
        </ToastContext.Provider>
    );
};
