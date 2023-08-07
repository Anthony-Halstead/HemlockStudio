import axios from 'axios';
import React, { useEffect, useState } from 'react'
import '../../css/reusables/product.css'
import { Link } from 'react-router-dom';
import '../../css/pages/cart.css'
import'../../css/reusables/linkbutton.css'
import'../../css/reusables/custombutton.css'
import { faBagShopping, faCashRegister } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function Cart(props) {
  useEffect(() => {
    findProductsInCart();
    getCartTotal();
  }, []);

  const [cartProducts, setCartProducts] = useState([]);
  const [cartTotal, setCartTotal] = useState(0);

const getCartTotal = () => {
  let jwtToken = localStorage.getItem("token");
    axios.get(`${process.env.REACT_APP_API_URL}/cart/total`, {
      headers: {
        'Authorization': `Bearer ${jwtToken}`
      }
    })
      .then((response) => {
        setCartTotal(response.data)
      })
      .catch((error) => {
        console.error('Error getting total from cart', error);
      })
};


  const handleRemoveFromCartClick = (productId) => {
    let jwtToken = localStorage.getItem("token");
    axios.delete(`${process.env.REACT_APP_API_URL}/cart/removeItemFromCart/${productId}`, {
      headers: {
        'Authorization': `Bearer ${jwtToken}`
      }
    })
      .then((response) => {
        props.setUpdateUser({});
        findProductsInCart();
        getCartTotal();
      })
      .catch((error) => {
        console.error('Error removing product from the cart', error);
      });
  };

  const findProductsInCart = () => {
    let jwtToken = localStorage.getItem("token");
    axios
      .get(`${process.env.REACT_APP_API_URL}/cart/findItemsInCart`, {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        setCartProducts(response.data);
      })
      .catch((error) => {
        console.error('Error fetching favorites', error);
      });
  }

  return (
    <div className='cart-page'>
      <div className='cart-container'>
      <h1>MY CART</h1>
      <div className='products-container'>
        {cartProducts.map(cartItem => (
          <div key={cartItem.product.id} className="cart-product">
            {cartItem.product.photoAlbum.length > 0 && (
              <img src={cartItem.product.photoAlbum[0].photoUrl} alt={cartItem.product.name} />
            )}
            <h2>{cartItem.product.name}</h2>
            <p>{cartItem.product.description}</p>
            <p>${cartItem.product.price.toFixed(2)}</p>
            <p>Quantity: {cartItem.quantity}</p>
            <button className='custom-button' onClick={() => handleRemoveFromCartClick(cartItem.product.id)}>Remove from Cart</button>
          </div>
        ))}
      </div>
      <h1>Cart Total: ${typeof cartTotal === 'number' ? cartTotal.toFixed(2) : '0.00'}</h1>
      <div className='link-button-container'>
        <Link className='link-button' to='/Checkout'>Go to Checkout</Link>
        <FontAwesomeIcon className='cart-icon' icon={faCashRegister} />
      </div>
      <div className='link-button-container'>
        <Link className='link-button' to='/store'>Return to shopping</Link>
        <FontAwesomeIcon className='cart-icon' icon={faBagShopping} />
      </div>
    </div>
    </div>
  );  
}

export default Cart