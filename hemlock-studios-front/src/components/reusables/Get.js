import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../../css/reusables/get.css';
import { useNavigate } from 'react-router';
import UpdateForm from './UpdateForm'; // Import the UpdateForm component

function Get({ entityType }) {
  const [data, setData] = useState([]);
  const [selectedItem, setSelectedItem] = useState(null);


  useEffect(() => {
    axios
      .get(`http://localhost:8080/${entityType}/findAll`)
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
    news: ['id', 'title', 'description', 'announcement', 'body', 'datePublished'],
  };

  const handleDelete = (id) => {
    axios
      .delete(`http://localhost:8080/${entityType}/delete`, { data: { id } })
      .then(() => {
        axios
          .get(`http://localhost:8080/${entityType}/findAll`)
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
    axios
      .put(`http://localhost:8080/${entityType}/update`, updatedItem)
      .then(() => {
        axios
          .get(`http://localhost:8080/${entityType}/findAll`)
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

  return (
    <div>
      <h2>{entityType.toUpperCase()} List</h2>
      <table className="table-container">
        <thead>
          <tr>
            <th></th>
            {entityFields[entityType].map((field) => (
              <th key={field}>{field}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {data.map((item) => (
            <tr key={item.id}>
              <td>
                <button onClick={() => handleUpdate(item.id)}>Update</button>
              </td>
              {entityType !== 'user' && (
                <td>
                  <button onClick={() => handleDelete(item.id)}>Delete</button>
                </td>
              )}
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
  );
}

export default Get;