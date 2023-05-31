import AdminPanel from '../reusables/AdminPanel'
import Draggable from 'react-draggable'; 
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Product from '../reusables/Product';

function Store(props) {

  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get('/product/products') 
      .then((response) => {
        setProducts(response.data);
      })
      .catch((error) => {
        console.error('Error fetching products', error);
      });
  }, []);



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
      </div> 
    )
  }
  else{
    return (   
       <div className="shop">
         {products.map(product => <Product key={product.id} product={product} />)}
       </div>)
  }
}

export default Store