import React, { useState, useEffect } from 'react'
import axios from 'axios'; // assuming you have axios installed, otherwise you can use fetch API

function Analytics() {
    const [analytics, setAnalytics] = useState(null);

    useEffect(() => {
      let jwtToken = localStorage.getItem("token");
        axios.get('https://3.16.219.108:8080/analytics/findAll',{
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