/**
 * @module Get
 * @description This module provides the Get component used for retrieving and displaying entity data.
 */

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../../css/reusables/get.css';
import UpdateForm from './UpdateForm';

/**
 * Get component that retrieves and renders entity data, such as users or news.
 * This component provides CRUD operations (Create, Read, Update, Delete) 
 * on the specified entity type, integrating with an external API.
 *
 * @function
 * @param {Object} props - Properties passed down to the Get component.
 * @param {string} props.entityType - The type of the entity to manage (e.g., 'user', 'news').
 * @returns {React.ReactNode} - The rendered Get component.
 *
 * @example
 * <Get entityType="user" />
 */
function Get({ entityType }) {
  /**
 * Local state for managing the retrieved entity data.
 * @type {[Array, React.Dispatch<React.SetStateAction<Array>>]}
 */
const [data, setData] = useState([]);

  /**
 * Local state for managing the currently selected entity item for CRUD operations.
 * @type {[Object|null, React.Dispatch<React.SetStateAction<Object|null>>]}
 */
const [selectedItem, setSelectedItem] = useState(null);


  useEffect(() => {
    let jwtToken = localStorage.getItem("token");
      /** Fetch data from the API and update the local state. */
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

  /**
 * Pre-defined fields to display for each entity type.
 * @type {Object.<string, Array.<string>>}
 */
  const entityFields = {
    user: ['id', 'username', 'email'],
    news: ['id', 'title', 'description', 'anouncement', 'body', 'datePublished'],
  };

  /**
 * Delete a specific entity item by its ID.
 * @param {number} id - The ID of the entity item to delete.
 */
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
/**
 * Update the `selectedItem` state to the entity item with the given ID.
 * @param {number} id - The ID of the entity item to update.
 */
  const handleUpdate = (id) => {
    const selectedItem = data.find((item) => item.id === id);
    setSelectedItem(selectedItem);
  };
  
/**
 * Reset the `selectedItem` state to null, effectively canceling the update.
 */
  const handleCancelUpdate = () => {
    setSelectedItem(null);
  };

  /**
 * Handle the submission of the update form.
 * @param {Object} updatedItem - The updated entity data.
 */
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