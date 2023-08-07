import React, { useState, useEffect, useContext } from 'react';
import { ToastContext } from '../reusables/ToastContext';
import '../../css/reusables/product.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart as faHeartSolid } from '@fortawesome/free-solid-svg-icons';
import { faHeart as faHeartRegular } from '@fortawesome/free-regular-svg-icons';
import axios from 'axios';
import AddToCartButton from './AddToCartButton';


function Product({ product, onClick, setUpdateUser }) {
  const [isFavorited, setIsFavorited] = useState(false);
  const { setMessage } = useContext(ToastContext);

  const handleAddedMessage = () => {
    setMessage('Item added to favorites');
};
const handleRemoveMessage = () => {
  setMessage('Item removed from favorites');
};

  useEffect(() => {
    let jwtToken = localStorage.getItem("token");
    axios
      .get(`${process.env.REACT_APP_API_URL}/user/findFavoriteProducts`, {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        const favoriteProductIds = response.data.map(product => product.id);
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


  const handleAddToCartClick = (event) => {
    event.stopPropagation();
    let jwtToken = localStorage.getItem("token");
 
    axios.post(`${process.env.REACT_APP_API_URL}/cart/addItemToCart`, { productId: product.id },{
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        setUpdateUser({});
      })
      .catch((error) => {
        console.error('Error adding product to the cart', error);
      });
  };

  const handleFavoritedClick = (event) => {
    event.stopPropagation();
    let jwtToken = localStorage.getItem("token");
    const favoriteDeleteData = product.id;
    if (isFavorited) {
      axios.delete(`${process.env.REACT_APP_API_URL}/user/removeProductFromFavorites/${favoriteDeleteData}`,{
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
        .then((response) => {
          setIsFavorited(false);
          setUpdateUser({});
          handleRemoveMessage();
        })
        .catch((error) => {
          console.error('Error removing product from favorites', error);
         
        });
    } else {
      axios.post(`${process.env.REACT_APP_API_URL}/user/addProductToFavorites`, { productId: product.id },{
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
        .then((response) => {
          setUpdateUser({});
          setIsFavorited(true);
         handleAddedMessage();
        })
        .catch((error) => {
          console.error('Error adding product to favorites', error);  
        });
    }
  };

  return (
    <div className="product">
      <div >
      <FontAwesomeIcon className='favorite-icon' icon={isFavorited ? faHeartSolid : faHeartRegular} onClick={handleFavoritedClick} />
      </div> 
      <div onClick={handleClick}>
        {product.photoAlbum.length > 0 && (
          <img src={product.photoAlbum[0].photoUrl} alt={product.name} />
        )}
        <h2>{product.name}</h2>
        <h1>${product.price.toFixed(2)}</h1>
        <div>
        <AddToCartButton onAddToCartClick={handleAddToCartClick} />
        </div>
      </div>
    </div>
  );
}

export default Product;