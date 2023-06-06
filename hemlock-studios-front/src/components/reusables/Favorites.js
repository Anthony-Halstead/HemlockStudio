import axios from 'axios';
import React, { useState, useEffect } from 'react';

function Favorites(props) {
  const [favorites, setFavorites] = useState([]);

  useEffect(() => {
    fetchFavorites();
  }, []);

  const fetchFavorites = () => {
    let jwtToken = localStorage.getItem("token");
    axios
      .get("http://localhost:8080/user/findFavoriteProducts", {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        setFavorites(response.data); // Set the favorites in state to the response data
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
    axios.delete(`http://localhost:8080/user/removeProductFromFavorites/${favoriteDeleteData}`,
    {
      headers: {
        'Authorization': `Bearer ${jwtToken}`
      }
    })
      .then((response) => { 
        fetchFavorites(); // Refresh favorites list
        props.updateUser({})
      })
      .catch((error) => {
        console.error('Error removing product from favorites', error);
      });
  };

  return (
    <div>
      {favorites.map((product, index) => (
        <div key={index}>
          {product.photoAlbum.length > 0 && (
            <img src={product.photoAlbum[0].photoUrl} alt={product.name} />
          )}
          <h2>{product.name}</h2>
          <p>{product.description}</p>
          <p>${product.price}</p>
          <button onClick={() => removeFavorite(product.id)}>Remove from favorites</button>
          {/* Add any other product details you want to display */}
        </div>
      ))}
    </div>
  );
}

export default Favorites;