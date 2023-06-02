import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from 'axios';
import React, { useState, useEffect } from 'react';
import '../../css/reusables/additive.css';
import { faSquareMinus, faSquarePlus } from '@fortawesome/free-solid-svg-icons';
import '../../css/pages/addproduct.css';

function AddNews() {
    const [anouncements, setAnouncements] = useState([]);
    const [selectedAnouncement, setSelectedAnouncement] = useState('NULL');

   
    useEffect(() => {
      axios
        .get('http://localhost:8080/enums/findAll')
        .then((response) => {
          const { anouncements} = response.data;
          setAnouncements(anouncements);
        })
        .catch((error) => {
          console.log(error);
        });
    }, []);
  
    const [newNews, setNewNews] = useState({
      title: "",
      description: "",
      body: "",
      imgUrls: [],
      anouncements: selectedAnouncement,
    });
  
    const inputFieldChangeHandler = (event) => {
      const name = event.target.name;
      const value = event.target.value;
      setNewNews((prevNews) => ({
        ...prevNews,
        [name]: value,
      }));
    };
  
    const addNewsChangeHandler = (event, index) => {
      const value = event.target.value;
      const newImgUrls = [...newNews.imgUrls];
      newImgUrls[index] = value;
      setNewNews((prevNews) => ({
        ...prevNews,
        imgUrls: newImgUrls,
      }));
    };
  
    const removeInputFieldHandler = () => {
      if (newNews.imgUrls.length > 0) {
        const newImgUrls = [...newNews.imgUrls];
        newImgUrls.pop();
        setNewNews((prevNews) => ({
          ...prevNews,
          imgUrls: newImgUrls,
        }));
      }
    };
  
    const addInputFieldHandler = () => {
      setNewNews((prevNews) => ({
        ...prevNews,
        imgUrls: [...prevNews.imgUrls, ''],
      }));
    };
  
    const handleAnouncementChange = (event) => {  
        setSelectedAnouncement(event.target.value);
    };
  
  
    const handleAddNewsSubmit = (event) => {
      event.preventDefault();
  
      const newsData = {
        title: newNews.title,
        description: newNews.description,
        body: newNews.body,
        imgUrls: newNews.imgUrls,
        anouncements: selectedAnouncement,
      };
  
      axios
        .post('http://localhost:8080/news/createNews', newsData)
        .then((response) => {
          console.log(response.data);
          setNewNews({
            title: "",
            description: "",
            body: "",
            imgUrls: [],
            anouncements: selectedAnouncement,
          });
        })
        .catch((error) => {
          console.log(error);
        });
    };
    
    return (
      <div className='add-product-content'>
       {/* Anouncement dropdown */}
  <select value={selectedAnouncement} onChange={handleAnouncementChange} name='anouncement'>
    {anouncements.map((anouncement) => (
      <option key={anouncement} value={anouncement}>
        {anouncement}
      </option>
    ))}
  </select>  
        <div>
          Title
          <input value={newNews.title} name='title' type='text' onChange={inputFieldChangeHandler}></input>
        </div>
        <div>
          Description
          <input value={newNews.description} name='description' type='text' onChange={inputFieldChangeHandler}></input>
        </div>
        <div>
          Body
          <input value={newNews.body} name='body' type='text' onChange={inputFieldChangeHandler}></input>
        </div>
        <div className='add-flex-column'>
          Images
          {newNews.imgUrls.map((url, index) => (
            <div key={index}>
              <input value={url} onChange={(event) => addNewsChangeHandler(event, index)} />
            </div>
          ))}
          <FontAwesomeIcon className='additive-icon' icon={faSquareMinus} onClick={removeInputFieldHandler} />
  
          <FontAwesomeIcon className='additive-icon' icon={faSquarePlus} onClick={addInputFieldHandler} />
        </div>
        <div>
          <button onClick={handleAddNewsSubmit}>SUBMIT</button>
        </div>
      </div>
    );
}

export default AddNews