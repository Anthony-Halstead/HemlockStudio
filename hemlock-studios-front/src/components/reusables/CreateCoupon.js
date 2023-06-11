import axios from 'axios';
import React, { useState } from 'react';
import '../../css/reusables/additive.css';
import '../../css/pages/addproduct.css';

function CreateCoupon() {
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

    axios
      .post('http://localhost:8080/coupon/createCoupon', couponData)
      .then((response) => {
        console.log(response.data);
        setNewCoupon({
          couponCode: "",
          discountValue: 0,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const isSubmitDisabled = couponCodeError || !newCoupon.couponCode || !newCoupon.discountValue;

  return (
    <div className='add-product-content'>
       <div>
        Discount %
        <input value={newCoupon.discountValue} name='discountValue' type='number' onChange={inputFieldChangeHandler}></input>
      </div>
      <div>
        Code
        <input value={newCoupon.couponCode} name='couponCode' type='text' onChange={inputFieldChangeHandler}></input>
        {couponCodeError && <div className="error-message">{couponCodeError}</div>}
      </div>
      <div>
        <button disabled={isSubmitDisabled} onClick={handleAddNewsSubmit}>SUBMIT</button>
      </div>
    </div>
  );
}

export default CreateCoupon;