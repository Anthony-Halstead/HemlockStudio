/**
 * @module UpdateNews
 * @description This module provides functionality for updating news.
 */

import React from 'react'
import Get from './Get';

/**
 * The `UpdateNews` component serves as a wrapper around the `Get` component to fetch news entities.
 * 
 * @function
 * @returns {React.Element} A React component displaying or updating news.
 */
function UpdateNews() {
  return (
    <Get entityType="news" />
  )
}

export default UpdateNews;
