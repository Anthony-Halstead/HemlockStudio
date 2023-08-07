import axios from 'axios';
import React, { useContext, useState } from 'react';
import { ToastContext } from '../reusables/ToastContext';
import '../../css/reusables/additive.css';
import '../../css/pages/addproduct.css';

function CreateCoupon() {

  const { setMessage } = useContext(ToastContext);

  const handleAddedMessage = () => {
    setMessage('New Coupon Was Created');
};

  const [newCoupon, setNewCoupon] = useState({
    couponCode: "",
    discountValue: 0,
  });
  const [couponCodeError, setCouponCodeError] = useState("");

  const inputFieldChangeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setNewCoupon((prevCoupon) => ({
      ...prevCoupon,
      [name]: value,
    }));
    validateCouponCode(value);
  };

  const validateCouponCode = (value) => {
    if (!/^[A-Z]{4}$/.test(value)) {
      setCouponCodeError("Coupon code must be exactly 4 uppercase letters.");
    } else {
      setCouponCodeError("");
    }
  };

  const handleAddNewsSubmit = (event) => {
    event.preventDefault();
    const couponData = {
      couponCode: newCoupon.couponCode,
      discountValue: parseFloat(newCoupon.discountValue),
    };
    let jwtToken = localStorage.getItem("token");
    axios
      .post(`${process.env.REACT_APP_API_URL}/coupon/createCoupon`, couponData, {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        setNewCoupon({
          couponCode: "",
          discountValue: 0,
        });
      })
      .catch((error) => {
        console.log(error);
        handleAddedMessage();
      });
  };

  const isSubmitDisabled = couponCodeError || !newCoupon.couponCode || !newCoupon.discountValue;

  return (
    <div className='add-product-content'>
       <div>
        Discount %
        <div className='input-container'>
        <input className='input-field' value={newCoupon.discountValue} name='discountValue' type='number' onChange={inputFieldChangeHandler}></input>
        </div>
      </div>
      <div>
        Code
        <div className='input-container'> 
        <input className='input-field'  value={newCoupon.couponCode} name='couponCode' type='text' onChange={inputFieldChangeHandler}></input>
        {couponCodeError && <div className="error-message">{couponCodeError}</div>}
      </div>
      </div>
      <div>
        <button disabled={isSubmitDisabled} onClick={handleAddNewsSubmit}>SUBMIT</button>
      </div>
    </div>
  );
}
export default CreateCoupon;