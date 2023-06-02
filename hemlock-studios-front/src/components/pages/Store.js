import AdminPanel from '../reusables/AdminPanel'
import Draggable from 'react-draggable'; 
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Product from '../reusables/Product';
import ProductOverlay from '../reusables/ProductOverlay';

function Store(props) {

  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);

  useEffect(() => {

    axios.get("http://localhost:8080/product/findAll") 
      .then((response) => {
        setProducts(response.data);
      })
      .catch((error) => {
        console.error('Error fetching products', error);
      });
  }, []);

  const openOverlay = (product) => {
    setSelectedProduct(product);
  };

  const closeOverlay = () => {
    setSelectedProduct(null);
  };

  if(props.user.roles.includes('ADMIN')){
    return (
      <div className='admin-content'>
        <div className='draggable-wrapper'>
        <Draggable

     defaultPosition={{x: 0, y: 0}}
     bounds={{top: -210, left: 0, right: 1000, bottom: 200}}
        >
          <div >
            <AdminPanel/>
          </div>
        </Draggable>
        </div>
        <div>
        {products.map((product) => (
            <Product key={product.id} product={product} onClick={openOverlay} />
          ))}
       </div>
       {selectedProduct && (
          <ProductOverlay product={selectedProduct} onClose={closeOverlay} />
        )}
      </div> 
    )
  }
  else{
    return (   
       <div className="shop">
        <div>
         {products.map((product) => (
            <Product key={product.id} product={product} onClick={openOverlay} />
          ))}
       </div>
        {selectedProduct && (
          <ProductOverlay product={selectedProduct} onClose={closeOverlay} />
        )}
        </div>
       )
  }
}

export default Store