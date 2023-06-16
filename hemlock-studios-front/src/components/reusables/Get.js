import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../../css/reusables/get.css';
import UpdateForm from './UpdateForm'; // Import the UpdateForm component

function Get({ entityType }) {
  const [data, setData] = useState([]);
  const [selectedItem, setSelectedItem] = useState(null);


  useEffect(() => {
    let jwtToken = localStorage.getItem("token");
    axios
      .get(`http://18.220.71.177:8080/${entityType}/findAll`, {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {
        console.error('Error fetching data', error);
      });
  }, [entityType]);

  const entityFields = {
    product: ['id', 'name', 'price', 'category', 'subcategory', 'size', 'discount', 'description'],
    user: ['id', 'username', 'email'],
    news: ['id', 'title', 'description', 'anouncement', 'body', 'datePublished'],
    coupon: ['id', 'couponCode', 'discountValue'],
  };

  const handleDelete = (id) => {
    let jwtToken = localStorage.getItem("token");
    console.log("Product ID",id)
    axios
      .delete(`http://18.220.71.177:8080/${entityType}/delete/${id}`,{
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then(() => {
        axios
          .get(`http://18.220.71.177:8080/${entityType}/findAll`, {
            headers: {
              'Authorization': `Bearer ${jwtToken}`
            }
          })
          .then((response) => {
            setData(response.data);
          })
          .catch((error) => {
            console.error('Error fetching data', error);
          });
      })
      .catch((error) => {
        console.error('Error deleting object', error);
      });
  };

  const handleUpdate = (id) => {
    const selectedItem = data.find((item) => item.id === id);
    setSelectedItem(selectedItem);
  };

  const handleCancelUpdate = () => {
    setSelectedItem(null);
  };
  
  const handleUpdateSubmit = (updatedItem) => {
 
    if (updatedItem.photoAlbum) {
      updatedItem.imgUrls = updatedItem.photoAlbum.map(photo => photo.photoUrl);
      delete updatedItem.photoAlbum;
    }
    
    if (updatedItem.photoReal) {
      
      updatedItem.photoReal = updatedItem.photoReal.map(photo => photo.photoUrl);
    }
  
    let jwtToken = localStorage.getItem("token");
    console.log("In the handle update submit with this product", updatedItem)
    axios
      .put(`http://18.220.71.177:8080/${entityType}/update`,  { id: selectedItem.id, ...updatedItem }, {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then(() => {
        console.log("In the handle update submit but now getting all of the products")
        axios
          .get(`http://18.220.71.177:8080/${entityType}/findAll`, {
            headers: {
              'Authorization': `Bearer ${jwtToken}`
            }
          })
          .then((response) => {
            setData(response.data);
          })
          .catch((error) => {
            console.error('Error fetching data', error);
          });
      })
      .catch((error) => {
        console.error('Error updating object', error);
      })
      .finally(() => {
        setSelectedItem(null);
      });
  };
  const isCouponEntity = entityType === 'coupon';

  return (
    <div className='table-container-color'>
    <div >
      <h2>{entityType.toUpperCase()} List</h2>
      <table className="table-container">
        <thead>
          <tr>
            {!isCouponEntity && <th></th>}
            <th></th>
            {entityFields[entityType].map((field) => (
              <th key={field}>{field}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {data.map((item) => (
            <tr key={item.id}>
              {!isCouponEntity && (
                <td>
                  <button onClick={() => handleUpdate(item.id)}>Update</button>
                </td>
              )}
              {entityType !== 'user' && (
                <td>
                  <button onClick={() => handleDelete(item.id)}>Delete</button>
                </td>
              )}
              {entityType === 'user' && <td></td>}
              {entityFields[entityType].map((field) => (
                <td key={field}>{item[field]}</td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>

      {selectedItem && (
        <UpdateForm
          selectedItem={selectedItem}
          entityType={entityType}
          onUpdate={handleUpdateSubmit}
          onCancel={handleCancelUpdate}
        />
      )}
    </div>
    </div>
  );
}

export default Get;