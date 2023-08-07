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
      .get(`${process.env.REACT_APP_API_URL}/${entityType}/findAll`, {
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
    user: ['id', 'username', 'email'],
    news: ['id', 'title', 'description', 'anouncement', 'body', 'datePublished'],
  };

  const handleDelete = (id) => {
    let jwtToken = localStorage.getItem("token");
    axios
      .delete(`${process.env.REACT_APP_API_URL}/${entityType}/delete/${id}`,{
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then(() => {
        axios
          .get(`${process.env.REACT_APP_API_URL}/${entityType}/findAll`, {
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
    axios
      .put(`${process.env.REACT_APP_API_URL}/${entityType}/update`,  { id: selectedItem.id, ...updatedItem }, {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then(() => {
        axios
          .get(`${process.env.REACT_APP_API_URL}/${entityType}/findAll`, {
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