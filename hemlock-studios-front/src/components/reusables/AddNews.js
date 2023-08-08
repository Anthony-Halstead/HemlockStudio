/**
 * @module AddNews
 */


/**
 * Utility from Font Awesome for React. Used for displaying icons in the React application.
 * @see {@link https://fontawesome.com/how-to-use/on-the-web/using-with/react|Font Awesome in React}
 * @external FontAwesomeIcon
 */
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

/**
 * Axios is a Promise-based HTTP client for JavaScript that can be used in the browser and Node.js environments.
 * It offers easy-to-use methods for making HTTP requests.
 * @see {@link https://github.com/axios/axios|Axios Documentation}
 * @external axios
 */
import axios from 'axios';

/**
 * Core functionalities from React used in this component.
 * - `useState`: Hook to declare state variables.
 * - `useEffect`: Hook to perform side effects in function components.
 * - `useContext`: Hook to access the context for this component.
 * @see {@link https://reactjs.org/|React Official Documentation}
 * @external React
 */
import React, { useState, useEffect, useContext } from 'react';

/**
 * Stylesheet for common reusables used across different components.
 * @external additive.css
 */
import '../../css/reusables/additive.css';

/**
 * Font Awesome icons used within the component.
 * - `faSquareMinus`: Represents a square with a minus sign.
 * - `faSquarePlus`: Represents a square with a plus sign.
 * @external FontAwesomeIcons
 */
import { faSquareMinus, faSquarePlus } from '@fortawesome/free-solid-svg-icons';

/**
 * Stylesheet specific to the `AddNews` and related components.
 * @external addproduct.css
 */
import '../../css/pages/addproduct.css';

/**
 * Context that provides toast notifications throughout the application.
 * This context provides a way to set and display toast messages to notify users.
 * @see {@link module:ToastContext}
 * @external ToastContext
 */
import { ToastContext } from '../reusables/ToastContext';

/**
 * DOMPurify is a DOM-only, super-fast, uber-tolerant XSS sanitizer for HTML, MathML and SVG.
 * It's written in JavaScript and works in all modern browsers (Safari, Opera (15+), Internet Explorer (10+), Edge,
 * Firefox, and Chrome - as well as almost anything else using Blink or WebKit).
 * It doesn't break on MSIE6 or other legacy browsers. It doesn't require any frameworks (but it works with all of them).
 * @see {@link https://github.com/cure53/DOMPurify|DOMPurify Documentation}
 * @external DOMPurify
 */
import DOMPurify from 'dompurify';


/**
 * The `AddNews` component allows users to create a new news item.
 * It provides form fields for inputting news details and performs necessary validations before
 * sending the data to the server via an API call.
 * 
 * @function
 * 
 * @returns {JSX.Element} The rendered AddNews component.
 */
function AddNews() {
     /**
     * Local state to hold the array of announcements fetched from the API.
     * @type {Array.<string>}
     */
     const [anouncements, setAnouncements] = useState([]);
      /**
     * Local state to hold the currently selected announcement from the dropdown.
     * @type {string}
     */
      const [selectedAnouncement, setSelectedAnouncement] = useState('NULL');
       /**
     * Context to show a toast message.
     */
       const { setMessage } = useContext(ToastContext);


   /**
     * Handles the event when a news item is added successfully.
     */
   const handleAddedMessage = () => {
    setMessage('A New News Item Was Created');
  };
  
   
     /**
     * This effect hook fetches a list of announcements from the API when the component mounts.
     */
     useEffect(() => {
      let jwtToken = localStorage.getItem('token');
      axios
      .get(`${process.env.REACT_APP_API_URL}/enums/findAll`, {
          headers: {
              Authorization: `Bearer ${jwtToken}`,
          },
      })
      .then((response) => {
          const { anouncements } = response.data;
          setAnouncements(anouncements);
      })
      .catch((error) => {
          console.log(error);
      });
  }, []);
  
   /**
     * State for the current news form data. This includes the title, description, body,
     * an array of image URLs, and the associated announcement.
     * @type {Object}
     */
   const [newNews, setNewNews] = useState({
    title: '',
    description: '',
    body: '',
    imgUrls: [],
    anouncement: selectedAnouncement,
  });
     /**
     * Sets the input field's value to the corresponding property in the `newNews` state.
     * This ensures that each input field's value is controlled and can be tracked.
     * 
     * @param {Event} event - The triggered event.
     */
    const inputFieldChangeHandler = (event) => {
      const name = event.target.name;
      const value = DOMPurify.sanitize(event.target.value);
      setNewNews((prevNews) => ({
        ...prevNews,
        [name]: value,
      }));
  };
  
     /**
     * Updates the image URL at a specific index in the `imgUrls` array of `newNews` state.
     * @param {Event} event - The triggered event.
     * @param {number} index - The index of the image URL to be updated.
     */
  const addNewsChangeHandler = (event, index) => {
      const value = DOMPurify.sanitize(event.target.value);
      const newImgUrls = [...newNews.imgUrls];
      newImgUrls[index] = value;
      setNewNews((prevNews) => ({
        ...prevNews,
        imgUrls: newImgUrls,
      }));
  };
  
   /**
     * Removes the last image URL from the `imgUrls` array in the `newNews` state.
     */
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
  
    /**
     * Adds an empty string to the `imgUrls` array in the `newNews` state. This represents
     * a new input field for an image URL in the UI.
     */
    const addInputFieldHandler = () => {
      setNewNews((prevNews) => ({
        ...prevNews,
        imgUrls: [...prevNews.imgUrls, ''],
      }));
    };
  
      /**
     * Handles the change of the selected announcement from the dropdown.
     * Updates the `selectedAnouncement` state with the newly selected value.
     * @param {Event} event - The triggered event.
     */
    const handleAnouncementChange = (event) => {  
        setSelectedAnouncement(event.target.value);
    };
  
  /**
     * Handles the addition of news items by sending the form data to the server.
     * 
     * @param {Event} event - The triggered event.
     */
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
      axios
       .post(`${process.env.REACT_APP_API_URL}/news/createNews`, newsData,
        {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        }
      )
        .then((response) => {
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

     /**
     * Prevents propagation of the onKeyDown event.
     * 
     * @param {Event} event - The triggered event.
     */
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