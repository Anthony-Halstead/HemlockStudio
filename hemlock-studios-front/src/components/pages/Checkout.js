import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import DefaultCreditCard from '../reusables/DefaultCreditCard';
import '../../css/pages/checkout.css'

function Checkout() {
  useEffect(() => {
    getCartTotal();
    getDiscountedCartTotal();
  }, []);


  const navigator = useNavigate()
  const [cartTotal, setCartTotal] = useState(0);
  const [couponCode, setCouponCode] = useState('');
  const [couponApplied, setCouponApplied] = useState(false);
  const [previousCouponCode, setPreviousCouponCode] = useState('');

  const handleSubmit = () => {
    let jwtToken = localStorage.getItem('token');
    axios.post('http://localhost:8080/cart/makePurchase',
        {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        }
      )
      .then((response) => {
        navigator('/receipt');
      })
      .catch((error) => {
        console.error('Error making purchase', error);
      });
  }


  const couponChangeHandler = (event) => {
    const value = event.target.value;
    setCouponCode(value);

    if (value !== previousCouponCode && couponApplied) {
      setCouponApplied(false);
      getCartTotal();
    }

    setPreviousCouponCode(value);
  };

  const debounce = (func, delay) => {
    let timer;
    return function (...args) {
      clearTimeout(timer);
      timer = setTimeout(() => {
        func.apply(this, args);
      }, delay);
    };
  };

  const handleCouponCode = debounce(() => {
    if (couponCode.trim() !== '') {
      console.log(couponCode);
      let jwtToken = localStorage.getItem('token');
      axios
        .post(
          'http://localhost:8080/coupon/applyCouponDiscount',
          { couponCode: couponCode },
          {
            headers: {
              Authorization: `Bearer ${jwtToken}`,
            },
          }
        )
        .then((response) => {
          setCouponApplied(true);
          getDiscountedCartTotal();
        })
        .catch((error) => {
          setCouponApplied(false);
          console.error('Error applying coupon discount', error);
          getCartTotal();
        });
    }
  }, 500); 
  
  const getCartTotal = () => {
    let jwtToken = localStorage.getItem('token');
    axios
      .get('http://localhost:8080/cart/total', {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
      })
      .then((response) => {
        setCartTotal(response.data);
      })
      .catch((error) => {
        console.error('Error getting total from cart', error);
      });
  };

  const getDiscountedCartTotal = () => {
    let jwtToken = localStorage.getItem('token');
    axios
      .get('http://localhost:8080/cart/discountedTotal', {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
      })
      .then((response) => {
        if(couponApplied === true)
      {setCartTotal(response.data);}
      })
      .catch((error) => {
        console.error('Error getting total from cart', error);
      });
  }

  return (
    <div className='checkout-content'>
      <DefaultCreditCard />
      <div>
        Enter Coupon Code:
        <input
          value={couponCode}
          type='text'
          placeholder='Coupon Code'
          onChange={couponChangeHandler}
          onKeyUp={handleCouponCode}
        />
      </div>
      <div>Cart Total: ${cartTotal.toFixed(2)}</div>
      <div>
        <button onClick={handleSubmit}>Submit</button>
      </div>
      <div>
        <Link to='/Cart'>-return to cart-</Link>
      </div>
      <div>
        <Link to='/store'>-return to shopping-</Link>
      </div>
    </div>
  );
}

export default Checkout;