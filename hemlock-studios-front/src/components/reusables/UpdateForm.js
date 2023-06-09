import React, { useState, useEffect } from 'react';

const UpdateForm = ({ selectedItem, entityType, onUpdate, onCancel }) => {
  const [updatedValues, setUpdatedValues] = useState({});

  useEffect(() => {
    setUpdatedValues(selectedItem);
  }, [selectedItem]);

  const handleFieldChange = (field, value, index = null) => {
    if (index !== null) {
      const newArray = [...updatedValues[field]];
      newArray[index].url = value;
      setUpdatedValues(prevValues => ({
        ...prevValues,
        [field]: newArray,
      }));
    } else {
      setUpdatedValues(prevValues => ({
        ...prevValues,
        [field]: value,
      }));
    }
  };

  const handleUpdateSubmit = (event) => {
    event.preventDefault();
    onUpdate(updatedValues);
  };

  return (
    <div>
      <h3>Update {entityType.toUpperCase()}</h3>
      <form onSubmit={handleUpdateSubmit}>
        {Object.keys(selectedItem).map((field) => (
          field !== 'id' && (
            <div key={field}>
              <label htmlFor={field}>{field}</label>
              {Array.isArray(updatedValues[field]) ? updatedValues[field].map((item, index) => (
                <div key={index}>
                  <input
                    type="text"
                    id={field}
                    value={item.photoUrl}
                    onChange={(e) => handleFieldChange(field, e.target.value, index)}
                  />
                </div>
              )) : (
                <input
                  type="text"
                  id={field}
                  value={updatedValues[field]}
                  onChange={(e) => handleFieldChange(field, e.target.value)}
                />
              )}
            </div>
          )
        ))}
        <button type="submit">Submit</button>
        <button type="button" onClick={onCancel}>
          Cancel
        </button>
      </form>
    </div>
  );
};

export default UpdateForm;