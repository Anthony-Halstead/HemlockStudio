import axios from 'axios';
import React, { useState, useEffect } from 'react';
import AddToCartButton from '../reusables/AddToCartButton';
import '../../css/pages/account.css'
function Favorites( props) {
  const [favorites, setFavorites] = useState([]);

  useEffect(() => {
    fetchFavorites();
  }, []);


  const handleAddToCartClick = (productId) => {


    console.log("FAVORITES", productId)
    let jwtToken = localStorage.getItem("token");
    const productData = productId;
    axios.post("http://3.16.219.108:8080/cart/addItemToCart", {productId},{
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        props.setUpdateUser({});
      })
      .catch((error) => {
        console.error('Error adding product to the cart', error);
      });
  };



  const fetchFavorites = () => {
    let jwtToken = localStorage.getItem("token");
    axios
      .get("http://3.16.219.108:8080/user/findFavoriteProducts", {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        setFavorites(response.data); 
      })
      .catch((error) => {
        console.error('Error fetching favorites', error);
      });
  }

  const removeFavorite = (productId) => {

    console.log("FAVORITES", productId)
    let jwtToken = localStorage.getItem("token");
    const favoriteDeleteData = productId;
    console.log(jwtToken)
    axios.delete(`http://3.16.219.108:8080/user/removeProductFromFavorites/${favoriteDeleteData}`,
    {
      headers: {
        'Authorization': `Bearer ${jwtToken}`
      }
    })
      .then((response) => { 
        fetchFavorites(); 
        props.updateUser({})
      })
      .catch((error) => {
        console.error('Error removing product from favorites', error);
      });
  };

  return (
    <div className='account-content-panel' >
   {favorites.map((product, index) => (
  <div key={index} className='account-panel-columns favorite-item'>
    {product.photoAlbum.length > 0 && (
      <img src={product.photoAlbum[0].photoUrl} alt={product.name} />
    )}
    <h2>{product.name}</h2>
    <p>${product.price.toFixed(2)}</p>
    <button className='custom-button' onClick={() => removeFavorite(product.id)}>Remove from favorites</button>
    <button className='custom-button' onClick={() => handleAddToCartClick(product.id)}>Add to cart</button>
  </div>
))}
     
    </div>
  );
}

export default Favorites;