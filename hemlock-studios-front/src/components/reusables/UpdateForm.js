import React, { useState } from 'react';

const UpdateForm = ({ selectedItem, entityType, onUpdate, onCancel }) => {
  const [updatedValues, setUpdatedValues] = useState({});

  const handleFieldChange = (field, value) => {
    setUpdatedValues((prevValues) => ({
      ...prevValues,
      [field]: value,
    }));
  };

  const handleUpdateSubmit = () => {
    const updatedItem = { ...updatedValues };
    onUpdate(updatedItem);
  };

  return (
    <div>
      <h3>Update {entityType.toUpperCase()}</h3>
      <form onSubmit={handleUpdateSubmit}>
        {Object.keys(selectedItem).map((field) => (
          // Exclude the ID fields from being editable
          field !== 'id' && (
            <div key={field}>
              <label htmlFor={field}>{field}</label>
              <input
                type="text"
                id={field}
                value={updatedValues[field] || selectedItem[field]}
                onChange={(e) => handleFieldChange(field, e.target.value)}
              />
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