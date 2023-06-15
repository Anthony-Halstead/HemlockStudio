import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from 'axios';
import React, { useState, useEffect, useContext } from 'react';
import '../../css/reusables/additive.css';
import { faSquareMinus, faSquarePlus } from '@fortawesome/free-solid-svg-icons';
import '../../css/pages/addproduct.css';
import { ToastContext } from '../reusables/ToastContext';

function AddNews() {
    const [anouncements, setAnouncements] = useState([]);
    const [selectedAnouncement, setSelectedAnouncement] = useState('NULL');
    const { setMessage } = useContext(ToastContext);

    const handleAddedMessage = () => {
      setMessage('A New News Item Was Created');
  };
  
   
    useEffect(() => {
      let jwtToken = localStorage.getItem('token');
      axios
        .get('http://localhost:8080/enums/findAll',
        {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        }
      )
        .then((response) => {
          const { anouncements} = response.data;
          setAnouncements(anouncements);
        })
        .catch((error) => {
          console.log(error);
        });
    }, []);
  
    const [newNews, setNewNews] = useState({
      title: '',
      description: '',
      body: '',
      imgUrls: [],
      anouncement: selectedAnouncement,
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
      let jwtToken = localStorage.getItem('token');
      const newsData = {
        title: newNews.title,
        description: newNews.description,
        body: newNews.body,
        imgUrls: newNews.imgUrls,
        anouncement: selectedAnouncement,
      };
  console.log(newNews.title)
  console.log(newNews.description)
  console.log(newNews.body)
  console.log(selectedAnouncement)
  console.log(newNews.imgUrls)
      axios
        .post('http://localhost:8080/news/createNews', newsData,
        {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        }
      )
        .then((response) => {
          console.log(response.data);
          setNewNews({
            title: '',
            description: '',
            body: '',
            imgUrls: [],
            anouncement: selectedAnouncement,
          });
        })
        .catch((error) => {
          console.log(error);
          handleAddedMessage();
        });
    };
    
    const handleKeyDown = (event) => {
      event.stopPropagation();
    }

    return (
      <div className='add-product-content'>
         <h1>Add News</h1>
         <div> Anouncement</div>
  <select value={selectedAnouncement} onChange={handleAnouncementChange} name='anouncements'>
   
    {anouncements.map((anouncement) => (
      <option key={anouncement} value={anouncement}>
        {anouncement}
      </option>
    ))}
  </select>  
        <div>
          Title
          <div className='input-container'>
          <input placeholder="Title..." className='input-field' value={newNews.title} name='title' type='text' onChange={inputFieldChangeHandler}></input>
          </div>
        </div>
        <div>
          Description
          <div className='input-container'>
          <input placeholder="Description..." className='input-field' value={newNews.description} name='description' type='text' onChange={inputFieldChangeHandler}></input>
          </div>
        </div>
        <div>
          Body
          <div className='input-container'>
          <textarea
          className='about-textarea'
          placeholder="Body..."
          value={newNews.body}
          name='body'
          type='text'
          onKeyDown={handleKeyDown}
          onChange={inputFieldChangeHandler}
        ></textarea>
          
          </div>
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
          <button className='submit-button' onClick={handleAddNewsSubmit}>SUBMIT</button>
        </div>
      </div>
    );
}

export default AddNews