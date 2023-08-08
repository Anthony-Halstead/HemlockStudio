/**
 * About Component - Provides information about Hemlock Studios and a contact form.
 * @module About
 * @requires react
 * @requires ../../css/pages/about.css
 * @requires react-draggable
 * @requires ../reusables/AdminPanel
 * @requires @fortawesome/react-fontawesome
 * @requires @fortawesome/free-brands-svg-icons
 * @requires axios
 */

import React, { useState } from 'react';
import '../../css/pages/about.css';
import Draggable from 'react-draggable';
import AdminPanel from '../reusables/AdminPanel';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faTwitter, faDiscord, faLinkedin, faYoutube, faTwitch
} from '@fortawesome/free-brands-svg-icons';
import axios from 'axios';

/**
 * About component displaying the about information and contact form.
 *
 * @function
 * @param {Object} props - Properties passed down from parent component.
 * @param {Object} props.user - User information.
 * @returns {JSX.Element}
 */
function About(props) {

  const [email, setEmail] = useState('');
  const [subject, setSubject] = useState('');
  const [message, setMessage] = useState('');

  /**
   * Sanitizes the input by removing any HTML tags.
   *
   * @function
   * @param {string} str - The string to sanitize.
   * @returns {string} - The sanitized string.
   */
  const sanitizeInput = (str) => str.replace(/(<([^>]+)>)/gi, "");

  /**
   * Handles changes to the email input.
   *
   * @function
   * @param {Event} e - The event object.
   */
  const handleEmailChange = (e) => setEmail(sanitizeInput(e.target.value));

  /**
   * Handles changes to the subject input.
   *
   * @function
   * @param {Event} e - The event object.
   */
  const handleSubjectChange = (e) => setSubject(sanitizeInput(e.target.value));

  /**
   * Handles changes to the message textarea.
   *
   * @function
   * @param {Event} e - The event object.
   */
  const handleMessageChange = (e) => setMessage(sanitizeInput(e.target.value));

  /**
   * Handles the submission of the contact form.
   *
   * @function
   * @param {Event} e - The event object.
   */
  const handleSubmit = (e) => {
    e.preventDefault();

    const payload = {
      email,
      subject,
      message,
    };

    let jwtToken = localStorage.getItem('token');
    axios.post(`${process.env.REACT_APP_API_URL}/email/contact-email`, payload,
        {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        }
      )
      .then((response) => {
        setEmail('');
      setSubject('');
      setMessage('');
        
      })
      .catch((error) => {
        alert('An error occurred while sending the email.',error);
      });
  }

  if(props.user.roles.includes('ADMIN')){
    return (
      <div >
           <div className='draggable-wrapper'>
        <Draggable

     defaultPosition={{x: 0, y: 0}}
     bounds={{ top: 0, left: 0, right: 1100, bottom: 900 }}
        >
          <div >
            <AdminPanel/>
          </div>
        </Draggable>
        </div>
        <div className='about-content'>
          <div className='about-container'>
      <div>
  <h1 className='about-contact-header'>Welcome To Hemlock Studios</h1>
  <div>
  <p>Established in September 2022, Hemlock Studios is the brainchild of two passionate gamers turned developers. Our journey started from hours spent navigating virtual worlds, and has since transformed into a mission to design our own realms and narratives that gamers worldwide can delve into.</p>
  <p>What sets Hemlock Studios apart? We are a lean, self-funded powerhouse. Our core team consists of a visionary artist/designer and a meticulous programmer. This blend of creativity and technical acumen allows us to build immersive experiences that push the boundaries of game development.</p>
  <p>We're proud to have embarked on the journey of creating our first title (currently untitled), an Action Role-Playing Game (ARPG) that marries rich storytelling with character development. In this intriguing universe, players will find fluid combat and defense mechanisms, an intricate crafting system, a diverse array of classes and abilities, and the thrill of exploring the game world on various mounts.
     But that's not all - we have an ambitious vision to port all our games into VR, bringing players closer to the action than ever before.</p>
  <p>At the heart of Hemlock Studios is an unwavering commitment to the player experience. We don't believe in cutting corners - every feature is carefully thought out and designed to deliver maximum enjoyment. It's not just about creating games; it's about crafting unforgettable adventures that captivate the mind and stir the imagination.</p>
  <p>Thank you for joining us on this exciting journey. We look forward to creating extraordinary gaming experiences that leave you on the edge of your seat, eagerly awaiting what's next from Hemlock Studios.</p>
</div></div>
          </div>
          <div class="circle-container">
  <div class="circle">
    <img src="" alt="personal picture"/>
  </div>
  <div class="circle">
    <img src="" alt="personal picture"/>
  </div>
</div>
        </div>
        <div className='contact-container'>
          <h1 className='about-contact-header'>Contact Us</h1>
          <form onSubmit={handleSubmit}>
        <h3>Enter Your Email</h3>
        <input
          className='about-text-field'
          type="text"
          placeholder="Email..."
          value={email}
          onChange={handleEmailChange}
        />
        <h3>Enter Subject</h3>
        <input
          className='about-text-field'
          type="text"
          placeholder="Subject..."
          value={subject}
          onChange={handleSubjectChange}
        />
        <h3>Enter Message</h3>
        <textarea
          className='about-textarea'
          placeholder="Type here..."
          value={message}
          onChange={handleMessageChange}
        ></textarea>
       <div><button className='submit-button' type="submit">Submit</button></div> 
      </form>
          <div className='about-logo-container' >
  <a href="https://twitter.com" target="_blank" rel="noopener noreferrer">
    <FontAwesomeIcon className='about-logo' icon={faTwitter} />
  </a>
  <a href="https://discord.com" target="_blank" rel="noopener noreferrer">
    <FontAwesomeIcon className='about-logo' icon={faDiscord} />
  </a>
  <a href="https://linkedin.com" target="_blank" rel="noopener noreferrer">
    <FontAwesomeIcon className='about-logo' icon={faLinkedin} />
  </a>
  <a href="https://youtube.com" target="_blank" rel="noopener noreferrer">
    <FontAwesomeIcon className='about-logo' icon={faYoutube} />
  </a>
  <a href="https://twitch.tv" target="_blank" rel="noopener noreferrer">
    <FontAwesomeIcon className='about-logo' icon={faTwitch} />
  </a>
</div>
      </div> 
      </div>
    )
  }
  else{
    return (<div><div className='about-content'>
    <div className='about-container'>
<div>
<h1 className='about-contact-header'>Welcome To Hemlock Studios</h1>
<div>
<p>Established in September 2022, Hemlock Studios is the brainchild of two passionate gamers turned developers. Our journey started from hours spent navigating virtual worlds, and has since transformed into a mission to design our own realms and narratives that gamers worldwide can delve into.</p>
<p>What sets Hemlock Studios apart? We are a lean, self-funded powerhouse. Our core team consists of a visionary artist/designer and a meticulous programmer. This blend of creativity and technical acumen allows us to build immersive experiences that push the boundaries of game development.</p>
<p>We're proud to have embarked on the journey of creating our first title (currently untitled), an Action Role-Playing Game (ARPG) that marries rich storytelling with character development. In this intriguing universe, players will find fluid combat and defense mechanisms, an intricate crafting system, a diverse array of classes and abilities, and the thrill of exploring the game world on various mounts.
But that's not all - we have an ambitious vision to port all our games into VR, bringing players closer to the action than ever before.</p>
<p>At the heart of Hemlock Studios is an unwavering commitment to the player experience. We don't believe in cutting corners - every feature is carefully thought out and designed to deliver maximum enjoyment. It's not just about creating games; it's about crafting unforgettable adventures that captivate the mind and stir the imagination.</p>
<p>Thank you for joining us on this exciting journey. We look forward to creating extraordinary gaming experiences that leave you on the edge of your seat, eagerly awaiting what's next from Hemlock Studios.</p>
</div></div>
    </div>
    <div class="circle-container">
<div class="circle">
<img src="https://th.bing.com/th/id/OIP.2BqhNdeyJ4Ol3eFDPtWxtwHaD4?w=331&h=180&c=7&r=0&o=5&dpr=1.1&pid=1.7" alt="personal picture"/>
</div>
<div class="circle">
<img src="https://th.bing.com/th/id/OIP.2BqhNdeyJ4Ol3eFDPtWxtwHaD4?w=331&h=180&c=7&r=0&o=5&dpr=1.1&pid=1.7" alt="personal picture"/>
</div>
</div>
  </div>
  <div className='contact-container'>
    <h1 className='about-contact-header'>Contact Us</h1>
    <form onSubmit={handleSubmit}>
  <h3>Enter Your Email</h3>
  <input
    className='about-text-field'
    type="text"
    placeholder="Email..."
    value={email}
    onChange={handleEmailChange}
  />
  <h3>Enter Subject</h3>
  <input
    className='about-text-field'
    type="text"
    placeholder="Subject..."
    value={subject}
    onChange={handleSubjectChange}
  />
  <h3>Enter Message</h3>
  <textarea
    className='about-textarea'
    placeholder="Type here..."
    value={message}
    onChange={handleMessageChange}
  ></textarea>
 <div><button className='submit-button' type="submit">Submit</button></div> 
</form>
    <div className='about-logo-container' >
<a href="https://twitter.com" target="_blank" rel="noopener noreferrer">
<FontAwesomeIcon className='about-logo' icon={faTwitter} />
</a>
<a href="https://discord.com" target="_blank" rel="noopener noreferrer">
<FontAwesomeIcon className='about-logo' icon={faDiscord} />
</a>
<a href="https://linkedin.com" target="_blank" rel="noopener noreferrer">
<FontAwesomeIcon className='about-logo' icon={faLinkedin} />
</a>
<a href="https://youtube.com" target="_blank" rel="noopener noreferrer">
<FontAwesomeIcon className='about-logo' icon={faYoutube} />
</a>
<a href="https://twitch.tv" target="_blank" rel="noopener noreferrer">
<FontAwesomeIcon className='about-logo' icon={faTwitch} />
</a>
</div>
</div> 
</div>)
  }
}

export default About