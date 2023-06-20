import React, { useState } from 'react';
import '../../css/reusables/productoverlay.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretLeft, faCaretRight, faCartShopping, faCircleXmark, faSquareXmark } from '@fortawesome/free-solid-svg-icons';
import AddToCartButton from './AddToCartButton';
import axios from 'axios';
import { Link } from 'react-router-dom';
import'../../css/reusables/linkbutton.css'

function ProductOverlay({ product, onClose, props }) {

  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  
  const handleAddToCartClick = (event) => {
    event.stopPropagation();
    let jwtToken = localStorage.getItem("token");
 
    console.log("PRODUCT ID", product.id)
    axios.post("https://18.220.71.177:8080/cart/addItemToCart", { productId: product.id },{
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        props.setUpdateUser({});
        // Handle the response, e.g. show a notification that the product was added to the cart
      })
      .catch((error) => {
        console.error('Error adding product to the cart', error);
      });
  };


  return (
    <div className="product-overlay">
      <div className="overlay-content">
        <FontAwesomeIcon icon={faSquareXmark} className="close-button" onClick={onClose}/>  
        <div className="product-image">
  {product.photoAlbum.length > 0 && (
    <>
    {product.photoAlbum.length > 1 && (
  <FontAwesomeIcon className="fa-caret-left" icon={faCaretLeft} onClick={() => setCurrentImageIndex(prev => (prev - 1 + product.photoAlbum.length) % product.photoAlbum.length)}/>
)}
<img src={product.photoAlbum[currentImageIndex].photoUrl} alt={product.name} />
{product.photoAlbum.length > 1 && (
  <FontAwesomeIcon className="fa-caret-right" icon={faCaretRight} onClick={() => setCurrentImageIndex(prev => (prev + 1) % product.photoAlbum.length)}/>
)}
    </>
  )}
</div>
        <div className="product-details overlay-content-box">
          <div>{product.name}</div>
          <div>{product.description}</div>
          <div>${product.price.toFixed(2)}</div>
          <div><AddToCartButton onAddToCartClick={handleAddToCartClick} /></div>
          <div className='link-button-cart-container'><Link className='link-button-cart'to='/Cart'>Go To Cart</Link>
          <FontAwesomeIcon className='cart-icon' icon={faCartShopping}/></div>
        </div>
      </div>
    </div>
  );
}

export default ProductOverlay;