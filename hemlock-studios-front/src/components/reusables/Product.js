import React, { useState, useEffect } from 'react';
import '../../css/reusables/product.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart as faHeartSolid } from '@fortawesome/free-solid-svg-icons';
import { faHeart as faHeartRegular } from '@fortawesome/free-regular-svg-icons';
import axios from 'axios';

function Product({ product, onClick, setUpdateUser }) {
  const [isFavorited, setIsFavorited] = useState(false);

  useEffect(() => {
    let jwtToken = localStorage.getItem("token");
    axios
      .get("http://localhost:8080/user/findFavoriteProducts", {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        // This maps the array of products to an array of product IDs
        const favoriteProductIds = response.data.map(product => product.id);
        // Checks if the current product's ID is in the list of favorite product IDs
        if (favoriteProductIds.includes(product.id)) {
          setIsFavorited(true);
        }
      })
      .catch((error) => {
        console.error('Error fetching favorites', error);
      });
  }, [product]);

  const handleClick = () => {
    onClick(product);
  };

  const handleFavoritedClick = (event) => {
    event.stopPropagation();
    let jwtToken = localStorage.getItem("token");

    const favoriteDeleteData = product.id;


    console.log("in the handle favorites click", jwtToken)
    if (isFavorited) {
      axios.delete(`http://localhost:8080/user/removeProductFromFavorites/${favoriteDeleteData}`,{
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
        .then((response) => {
          setIsFavorited(false);
          setUpdateUser({});
          // Handle the response, e.g. show a notification that the product was removed from favorites
        })
        .catch((error) => {
          console.error('Error removing product from favorites', error);
          // Handle the error, e.g. show an error message
        });
    } else {
      axios.post("http://localhost:8080/user/addProductToFavorites", { productId: product.id },{
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
        .then((response) => {
          setUpdateUser({});
          setIsFavorited(true);
          // Handle the response, e.g. show a notification that the product was added to favorites
        })
        .catch((error) => {
          console.error('Error adding product to favorites', error);
          // Handle the error, e.g. show an error message
        });
    }
  };

  return (
    <div className="product">
      <FontAwesomeIcon icon={isFavorited ? faHeartSolid : faHeartRegular} onClick={handleFavoritedClick} />
      <div onClick={handleClick}>
        {product.photoAlbum.length > 0 && (
          <img src={product.photoAlbum[0].photoUrl} alt={product.name} />
        )}
        <h2>{product.name}</h2>
        <p>{product.description}</p>
        <p>${product.price}</p>
      </div>
    </div>
  );
}

export default Product;