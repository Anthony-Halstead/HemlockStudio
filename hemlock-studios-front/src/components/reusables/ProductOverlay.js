import React from 'react';
import '../../css/reusables/productoverlay.css';

function ProductOverlay({ product, onClose }) {
  return (
    <div className="product-overlay">
      <div className="overlay-content">
        <button className="close-button" onClick={onClose}>
          X
        </button>
        <div className="product-details">
          {product.photoAlbum.length > 0 && (
            <img src={product.photoAlbum[0].photoUrl} alt={product.name} />
          )}
          <h2>{product.name}</h2>
          <p>{product.description}</p>
          <p>${product.price}</p>
        </div>
      </div>
    </div>
  );
}

export default ProductOverlay;