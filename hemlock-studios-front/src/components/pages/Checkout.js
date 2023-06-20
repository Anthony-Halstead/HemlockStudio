import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import DefaultCreditCard from '../reusables/DefaultCreditCard';
import '../../css/pages/checkout.css'
import'../../css/reusables/linkbutton.css'
import'../../css/reusables/custombutton.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBagShopping, faCartShopping } from '@fortawesome/free-solid-svg-icons';
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
    axios.post('http://3.16.219.108:8080/cart/makePurchase',{},
        {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        }
      )
      .then((response) => {
        navigator('/Receipt');
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
          'http://3.16.219.108:8080/coupon/applyCouponDiscount',
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
      .get('http://3.16.219.108:8080/cart/total', {
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
      .get('http://3.16.219.108:8080/cart/discountedTotal', {
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
      <div className='checkout-container'>
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
        <button className='submit-button' onClick={handleSubmit}>Make Purchase</button>
      </div>
      <div className='link-button-container'>
     
        <Link className='link-button' to='/Cart'>Return to cart</Link>
        <FontAwesomeIcon className='cart-icon' icon={faCartShopping}/>
      </div>
      <div className='link-button-container'>
       
        <Link  className='link-button' to='/store'>Return to shopping</Link>
        <FontAwesomeIcon  className='cart-icon' icon={faBagShopping} />
      </div>
    </div>
    </div>
  );
}

export default Checkout;