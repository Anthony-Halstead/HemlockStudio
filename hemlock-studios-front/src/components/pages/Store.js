import AdminPanel from '../reusables/AdminPanel'
import Draggable from 'react-draggable'; 
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Product from '../reusables/Product';
import ProductOverlay from '../reusables/ProductOverlay';
import '../../css/pages/admin.css'
function Store(props) {
console.log("In the Store Path", props)
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
<div>
      <div className='draggable-wrapper'>
        <Draggable

     defaultPosition={{x: 0, y: 0}}
     bounds={{ top: 0, left: 0, right: 1100, bottom: 900 }}
        >
          <div >
            <AdminPanel/>
          </div>
        </Draggable>
        </div>
      <div className='admin-content'>
       
        <div>
        {products.map((product) => (
            <Product  setUpdateUser={props.setUpdateUser} key={product.id} product={product} onClick={openOverlay}/>
          ))}
       </div>
       {selectedProduct && (
          <ProductOverlay product={selectedProduct} onClose={closeOverlay} />
        )}
      </div> 
      </div>
    )
  }
  else{
    return (   
       <div className="shop">
        <div>
         {products.map((product) => (
            <Product  setUpdateUser={props.setUpdateUser} key={product.id} product={product} onClick={openOverlay} />
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