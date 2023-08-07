import axios from 'axios';
import React, { useEffect, useState } from 'react'

function DefaultCreditCard() {

const [creditCard, setCreditCard] = useState({});

    useEffect(() => {
        let jwtToken = localStorage.getItem("token");
          axios
            .get(`${process.env.REACT_APP_API_URL}/user/getDefaultCreditCard`, {
              headers: {
                'Authorization': `Bearer ${jwtToken}`
              }
            })
            .then((response) => {
              setCreditCard(response.data);
            })
            .catch((error) => {
              console.error('Error fetching credit card', error);
            });
        
      }, []);
    
      return (
        <div>
          {creditCard.cardNumber && (
            <div>
              Card Number: {creditCard.cardNumber}
            </div>
          )}
          {creditCard.expirationDate && (
            <div>
              Expiration Date: {creditCard.expirationDate}
            </div>
          )}
          {!creditCard.cardNumber && (
            <div>
              Please add a credit card.
            </div>
          )}
        </div>
      );
}

export default DefaultCreditCard