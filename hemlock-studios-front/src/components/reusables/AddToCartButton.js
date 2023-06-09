import React from 'react'

function AddToCartButton({ onAddToCartClick }) {
  return (
    <button onClick={onAddToCartClick}>Add to Cart</button>
  );
}

export default AddToCartButton