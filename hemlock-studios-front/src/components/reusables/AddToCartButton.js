import '../../css/reusables/addtocart.css'
import React, { useContext } from 'react';
import { ToastContext } from '../reusables/ToastContext';

function AddToCartButton({ onAddToCartClick }) {
  const { setMessage } = useContext(ToastContext);
  const handleClick = () => {
    setMessage('Item added to cart');
};


const handleButtonClick = (event) => {
  onAddToCartClick(event);
  handleClick();
};

  return (
    <button className='add-to-cart-button' onClick={handleButtonClick}>Add to Cart</button>
  );
}

export default AddToCartButton