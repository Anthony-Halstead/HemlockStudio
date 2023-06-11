import React, { useState, useEffect } from 'react';
import axios from 'axios';

const UpdateForm = ({ selectedItem, entityType, onUpdate, onCancel }) => {
  const [updatedValues, setUpdatedValues] = useState({});
  const [categories, setCategories] = useState([]);
  const [subcategories, setSubcategories] = useState([]);
  const [anouncement, setAnouncement] = useState([]);
  const [sizes, setSizes] = useState([]);
  const [newPhotoUrl, setNewPhotoUrl] = useState('');
  useEffect(() => {
    setUpdatedValues(selectedItem);
    axios
      .get('http://localhost:8080/enums/findAll')
      .then((response) => {
        const { categories, subcategories, sizes, anouncements } = response.data;
        setCategories(categories);
        setSubcategories(subcategories);
        setSizes(sizes);
        setAnouncement(anouncements); 
      })
      .catch((error) => {
        console.log(error);
      });
  }, [selectedItem]);

  const handleFieldChange = (field, value) => {
    setUpdatedValues((prevValues) => ({
      ...prevValues,
      [field]: value === '' ? null : value,
    }));
  };


  const handlePhotoUrlChange = (field, index, value) => {
    setUpdatedValues((prevValues) => {
      const newPhotoAlbum = [...prevValues[field]];
      newPhotoAlbum[index].photoUrl = value;
      return { ...prevValues, [field]: newPhotoAlbum };
    });
  };
  
  const handleRemovePhoto = (field, index) => {
    setUpdatedValues((prevValues) => {
      const newPhotoAlbum = [...prevValues[field]];
      newPhotoAlbum.splice(index, 1);
      return { ...prevValues, [field]: newPhotoAlbum };
    });
  };
  
  const handlePhotoRealUrlChange = (field, index, value) => {
    setUpdatedValues((prevValues) => {
      const newPhotoReal = [...prevValues[field]];
      newPhotoReal[index].photoUrl = value;
      return { ...prevValues, [field]: newPhotoReal };
    });
  };
  
  const handleAddPhotoReal = (field) => {
    setUpdatedValues((prevValues) => {
      const newPhotoReal = [...prevValues[field], { photoUrl: newPhotoUrl }];
      return { ...prevValues, [field]: newPhotoReal };
    });
    setNewPhotoUrl('');
  };
  
  const handleRemovePhotoReal = (field, index) => {
    setUpdatedValues((prevValues) => {
      const newPhotoReal = [...prevValues[field]];
      newPhotoReal.splice(index, 1);
      return { ...prevValues, [field]: newPhotoReal };
    });
  };

  const handleAddPhoto = (field) => {
    setUpdatedValues((prevValues) => {
      const newPhotoAlbum = [...prevValues[field], { photoUrl: newPhotoUrl }];
      return { ...prevValues, [field]: newPhotoAlbum };
    });
    setNewPhotoUrl('');
  };

  const handleUpdateSubmit = (event) => {
    event.preventDefault();
    onUpdate(updatedValues);
  };

  const renderFormField = (field) => {
    // If the field is 'id', do not render anything
    if (field === 'id') return null;
    
    if (field === 'photoAlbum' || field === 'photoReal') {
      const localHandlePhotoUrlChange = field === 'photoAlbum' ? handlePhotoUrlChange : handlePhotoRealUrlChange;
      const localHandleAddPhoto = field === 'photoAlbum' ? handleAddPhoto : handleAddPhotoReal;
      const localHandleRemovePhoto = field === 'photoAlbum' ? handleRemovePhoto : handleRemovePhotoReal;
      
      return (
        <>
          {updatedValues[field].map((photo, index) => (
            <div key={index}>
              <label>{`Photo ${index + 1} URL`}</label>
              <input
                type="text"
                value={photo.photoUrl || ''}
                onChange={(e) =>
                  localHandlePhotoUrlChange(field, index, e.target.value)
                }
              />
              <button type="button" onClick={() => localHandleRemovePhoto(field, index)}>
                Remove
              </button>
            </div>
          ))}
          <div>
            <label>Add new photo</label>
            <input
              type="text"
              value={newPhotoUrl}
              onChange={(e) => setNewPhotoUrl(e.target.value)}
            />
            <button type="button" onClick={() => localHandleAddPhoto(field)}>
              Add
            </button>
          </div>
        </>
      );
    }  else if (field === 'subcategory') {
      return (
        <select
          value={updatedValues[field] || ''}
          onChange={(e) => handleFieldChange(field, e.target.value)}
        >
          {subcategories.map((subcategory) => (
            <option key={subcategory} value={subcategory}>
              {subcategory}
            </option>
          ))}
        </select>
      );
    } else if (field === 'size') {
      return (
        <select
          value={updatedValues[field] || ''}
          onChange={(e) => handleFieldChange(field, e.target.value)}
        >
          {sizes.map((size) => (
            <option key={size} value={size}>
              {size}
            </option>
          ))}
        </select>
      );
    } else if (field === 'anouncement') {
      return (
        <select
          value={updatedValues[field] || ''}
          onChange={(e) => handleFieldChange(field, e.target.value)}
        >
          {anouncement.map((anouncementOption) => (
            <option key={anouncementOption} value={anouncementOption}>
              {anouncementOption}
            </option>
          ))}
        </select>
      );
    } else {
      return (
        <input
          type="text"
          value={updatedValues[field] || ''}
          onChange={(e) => handleFieldChange(field, e.target.value)}
        />
      );
    }
  };

  return (
    <form onSubmit={handleUpdateSubmit}>
      {Object.keys(updatedValues).map((field) => (
        <div key={field}>
          <label>{field}</label>
          {renderFormField(field)}
        </div>
      ))}
      <button type="submit">Update</button>
      <button type="button" onClick={onCancel}>
        Cancel
      </button>
    </form>
  );
};

export default UpdateForm;