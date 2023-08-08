/**
 * Analytics module.
 * 
 * This module provides the Analytics component, which fetches and displays
 * analytics data about products and revenue from an API endpoint.
 * 
 * @module Analytics
 */

/**
 * React's default import. Required to define and use React components.
 * 
 * @see {@link https://reactjs.org/|React Official Documentation}
 * @external React
 */
import React, { useState, useEffect } from 'react';

/**
 * Axios is a promise-based HTTP client for the browser and Node.js.
 * 
 * @see {@link https://github.com/axios/axios|Axios GitHub Documentation}
 * @external axios
 */
import axios from 'axios'; 

/**
 * Analytics Component.
 * 
 * This component fetches analytics data from the server once it is mounted.
 * Once data is fetched, it displays a table with product analytics and
 * a section with revenue analytics.
 * 
 * @returns {React.Element} Rendered Analytics component.
 */
function Analytics() {
    /**
     * State variable to hold the fetched analytics data.
     * 
     * @type {[Object, Function]}
     */
    const [analytics, setAnalytics] = useState(null);

    /**
     * useEffect hook to fetch analytics data once the component is mounted.
     */
    useEffect(() => {
      let jwtToken = localStorage.getItem("token");
        axios.get(`${process.env.REACT_APP_API_URL}/analytics/findAll`,{
          headers: {
            'Authorization': `Bearer ${jwtToken}`
          }
        })
            .then(response => setAnalytics(response.data))
            .catch(err => console.error(err));
    }, []);


    if (!analytics) {
        return <div>Loading...</div>;
    }

    return (
      <div className='add-product-content'>
          <h1>ANALYTICS</h1>
          <div>
              <h2>Product Analytics</h2>
              <table style={{borderCollapse: "collapse", width: "100%"}}>
                  <thead>
                      <tr>
                          <th style={{border: "1px solid black", padding: "10px"}}>Product ID</th>
                          <th style={{border: "1px solid black", padding: "10px"}}>Product Name</th>
                          <th style={{border: "1px solid black", padding: "10px"}}>Quantity Sold</th>
                      </tr>
                  </thead>
                  <tbody>
                      {analytics.productDataList.map(product => (
                          <tr key={product.productId}>
                              <td style={{border: "1px solid black", padding: "10px"}}>{product.productId}</td>
                              <td style={{border: "1px solid black", padding: "10px"}}>{product.productName}</td>
                              <td style={{border: "1px solid black", padding: "10px"}}>{product.quantitySold}</td>
                          </tr>
                      ))}
                  </tbody>
              </table>
          </div>
          <div>
              <h2>Revenue Analytics</h2>
              <p>Total Revenue: ${analytics.totalRevenue.toFixed(2)}</p>
          </div>
      </div>
  );
}

export default Analytics;