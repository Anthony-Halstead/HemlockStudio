import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../../css/reusables/get.css'

/**
 * @module UpdateForm
 */

/**
 * UpdateForm is a React functional component used for updating various entity types.
 * 
 * @param {Object} props
 * @param {Object} props.selectedItem - The currently selected entity to be updated.
 * @param {string} props.entityType - The type of entity being updated.
 * @param {Function} props.onUpdate - Callback function when an update is submitted.
 * @param {Function} props.onCancel - Callback function when the update form is canceled.
 * @returns {JSX.Element} The rendered component.
 */
const UpdateForm = ({ selectedItem, entityType, onUpdate, onCancel }) => {
  const [updatedValues, setUpdatedValues] = useState({});
  const [anouncement, setAnouncement] = useState([]);
  const [newPhotoUrl, setNewPhotoUrl] = useState('');

 /**
   * Effect hook that initializes the form values based on the selected item and fetches
   * required enumeration values.
   */
  useEffect(() => {
    setUpdatedValues(selectedItem);
    let jwtToken = localStorage.getItem("token");
    axios
      .get(`${process.env.REACT_APP_API_URL}/enums/findAll`, {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        const { anouncements } = response.data;
        setAnouncement(anouncements); 
      })
      .catch((error) => {
        console.log(error);
      });
  }, [selectedItem]);
/**
 * Handle changes to generic form fields.
 * 
 * @function
 * @param {string} field - The field name of the entity's attribute.
 * @param {string|number} value - The updated value for the specified field.
 */
  const handleFieldChange = (field, value) => {
    setUpdatedValues((prevValues) => ({
      ...prevValues,
      [field]: value === '' ? null : value,
    }));
  };

/**
 * Handle changes to the photo URL within a photo album.
 * 
 * @function
 * @param {string} field - The field name representing the photo album.
 * @param {number} index - Index of the photo URL in the album to be updated.
 * @param {string} value - The updated photo URL.
 */
  const handlePhotoUrlChange = (field, index, value) => {
    setUpdatedValues((prevValues) => {
      const newPhotoAlbum = [...prevValues[field]];
      newPhotoAlbum[index].photoUrl = value;
      return { ...prevValues, [field]: newPhotoAlbum };
    });
  };
 /**
 * Remove a photo from the photo album.
 * 
 * @function
 * @param {string} field - The field name representing the photo album.
 * @param {number} index - Index of the photo in the album to be removed.
 */ 
  const handleRemovePhoto = (field, index) => {
    setUpdatedValues((prevValues) => {
      const newPhotoAlbum = [...prevValues[field]];
      newPhotoAlbum.splice(index, 1);
      return { ...prevValues, [field]: newPhotoAlbum };
    });
  };
  /**
 * Handle changes to the photo reel URL.
 * 
 * @function
 * @param {string} field - The field name representing the real photo album.
 * @param {number} index - Index of the photo URL in the real album to be updated.
 * @param {string} value - The updated reel photo URL.
 */
  const handlePhotoRealUrlChange = (field, index, value) => {
    setUpdatedValues((prevValues) => {
      const newPhotoReal = [...prevValues[field]];
      newPhotoReal[index].photoUrl = value;
      return { ...prevValues, [field]: newPhotoReal };
    });
  };
  
  /**
 * Add a new photo reel to the entity.
 * 
 * @function
 * @param {string} field - The field name representing the photo reel album.
 */
  const handleAddPhotoReal = (field) => {
    setUpdatedValues((prevValues) => {
      const newPhotoReal = [...prevValues[field], { photoUrl: newPhotoUrl }];
      return { ...prevValues, [field]: newPhotoReal };
    });
    setNewPhotoUrl('');
  };
  
  /**
 * Remove a photo reel from the entity.
 * 
 * @function
 * @param {string} field - The field name representing the real photo album.
 * @param {number} index - Index of the reel photo in the album to be removed.
 */
  const handleRemovePhotoReal = (field, index) => {
    setUpdatedValues((prevValues) => {
      const newPhotoReal = [...prevValues[field]];
      newPhotoReal.splice(index, 1);
      return { ...prevValues, [field]: newPhotoReal };
    });
  };

  /**
 * Add a new photo to the photo album of the entity.
 * 
 * @function
 * @param {string} field - The field name representing the photo album.
 */
  const handleAddPhoto = (field) => {
    setUpdatedValues((prevValues) => {
      const newPhotoAlbum = [...prevValues[field], { photoUrl: newPhotoUrl }];
      return { ...prevValues, [field]: newPhotoAlbum };
    });
    setNewPhotoUrl('');
  };

  /**
 * Handle submission of the update form.
 * 
 * @function
 * @param {Event} event - The form submission event.
 */
  const handleUpdateSubmit = (event) => {
    event.preventDefault();
    onUpdate(updatedValues);
  };

   /**
   * Render the appropriate form field based on the entity attribute.
   * 
   * @param {string} field - The attribute name of the entity.
   * @returns {JSX.Element} The appropriate form field.
   */
  const renderFormField = (field) => {
    if (field === 'id') return null;
    if (field === 'photoAlbum' || field === 'photoReal') {
      const localHandlePhotoUrlChange = field === 'photoAlbum' ? handlePhotoUrlChange : handlePhotoRealUrlChange;
      const localHandleAddPhoto = field === 'photoAlbum' ? handleAddPhoto : handleAddPhotoReal;
      const localHandleRemovePhoto = field === 'photoAlbum' ? handleRemovePhoto : handleRemovePhotoReal;
      
      return (
        <div className='table-container-color'>
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
        </div> );
    } else if (field === 'anouncement') {
      return (<div className='table-container-color'>
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
        </div>  );
    } else {
      return (<div className='table-container-color'>
        <input
          type="text"
          value={updatedValues[field] || ''}
          onChange={(e) => handleFieldChange(field, e.target.value)}
        />
     </div> ); 
    }
  };

  return (
    <div className='table-container-color'>
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
    </div>
  );
};

export default UpdateForm;