import React from 'react';
import '../../css/reusables/product.css';

function Product({ product, onClick }) {

  const handleClick = () => {
    onClick(product);
  };
  
  return (
    <div className="product" onClick={handleClick}>
      {product.photoAlbum.length > 0 && (
        <img src={product.photoAlbum[0].photoUrl} alt={product.name} />
      )}
      <h2>{product.name}</h2>
      <p>{product.description}</p>
      <p>${product.price}</p>
    </div>
  );
}

export default Product;