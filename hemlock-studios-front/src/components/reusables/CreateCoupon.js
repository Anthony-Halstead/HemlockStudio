import axios from 'axios';
import React, { useState } from 'react';
import '../../css/reusables/additive.css';
import '../../css/pages/addproduct.css';

function CreateCoupon() {

  const [newCoupon, setNewCoupon] = useState({
    code: "",
    discount: 0,
  });

  const inputFieldChangeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setNewCoupon((prevCoupon) => ({
      ...prevCoupon,
      [name]: value,
    }));
  };

  const handleAddNewsSubmit = (event) => {
    event.preventDefault();
    const couponData = {
      code: newCoupon.code,
      discount: parseFloat(newCoupon.discount)
    };

    axios
      .post('http://localhost:8080/coupon/createCoupon', couponData)
      .then((response) => {
        console.log(response.data);
        setNewCoupon({
          code: "",
          discount: 0,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };
  
  return (
    <div className='add-product-content'> 
      <div>
        Code
        <input value={newCoupon.code} name='code' type='text' onChange={inputFieldChangeHandler}></input>
      </div>
      <div>
        Discount %
        <input value={newCoupon.discount} name='discount' type='number' onChange={inputFieldChangeHandler}></input>
      </div>
      <div>
        <button onClick={handleAddNewsSubmit}>SUBMIT</button>
      </div>
    </div>
  );
}

export default CreateCoupon