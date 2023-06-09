import axios from 'axios';
import React, { useEffect, useState } from 'react'
import '../../css/reusables/product.css'
import { Link } from 'react-router-dom';

function Cart(props) {
  useEffect(() => {
    findProductsInCart();
    getCartTotal();
  }, []);

  const [cartProducts, setCartProducts] = useState([]);
  const [cartTotal, setCartTotal] = useState(0);

const getCartTotal = () => {
  let jwtToken = localStorage.getItem("token");
  
    axios.get("http://localhost:8080/cart/total", {
      headers: {
        'Authorization': `Bearer ${jwtToken}`
      }
    })
      .then((response) => {
        setCartTotal(response.data)
      })
      .catch((error) => {
        console.error('Error getting total from cart', error);
        // Handle the error, e.g. show an error message
      })
};




  const handleRemoveFromCartClick = (productId) => {
    let jwtToken = localStorage.getItem("token");
    console.log("PRODUCT ID", productId)
    axios.delete(`http://localhost:8080/cart/removeItemFromCart/${productId}`, {
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
        // Handle the error, e.g. show an error message
      });
  };


  const findProductsInCart = () => {
    let jwtToken = localStorage.getItem("token");
    axios
      .get("http://localhost:8080/cart/findItemsInCart", {
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
    <div>
      <h1>My Cart</h1>
      {cartProducts.map(cartItem => (<div className="product">
        <div key={cartItem.product.id}>
          {cartItem.product.photoAlbum.length > 0 && (
            <img src={cartItem.product.photoAlbum[0].photoUrl} alt={cartItem.product.name} />
          )}
          <h2>{cartItem.product.name}</h2>
          <p>{cartItem.product.description}</p>
          <p>${cartItem.product.price}</p>
          <p>Quantity: {cartItem.quantity}</p>
          <button onClick={() => handleRemoveFromCartClick(cartItem.product.id)}>Remove from Cart</button>
        </div>
      </div>
      ))}
      <div>Cart Total: ${cartTotal}</div>
      <Link to='/Checkout'>Go to Checkout</Link>
      <div>
      <Link to='/store'>-return to shopping-</Link>
      </div>
    </div>
  );
}

export default Cart