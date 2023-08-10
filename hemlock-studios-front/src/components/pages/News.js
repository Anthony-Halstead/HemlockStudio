/**
 * @module News
 */

import React, { useEffect, useState } from 'react';

/**
 * AdminPanel component for administrators.
 * @see {@link module:AdminPanel|AdminPanel}
 */
import AdminPanel from '../reusables/AdminPanel';

/**
 * Component for draggable functionality.
 */
import Draggable from 'react-draggable';

/**
 * Styles for the News page.
 * @see {@link ../../css/pages/news.css|News CSS}
 */
import '../../css/pages/news.css';

/**
 * Axios library for making HTTP requests.
 */
import axios from 'axios';

/**
 * Article component to display individual news articles.
 * @see {@link module:Article|Article}
 */
import Article from '../reusables/Article';

/**
 * Overlay component for displaying detailed news.
 * @see {@link module:NewsOverlay|NewsOverlay}
 */
import NewsOverlay from '../reusables/NewsOverlay';

/**
 * The News component displays a list of news articles. If the logged-in user is an administrator,
 * an admin panel is shown which can be dragged around the screen.
 * 
 * When a user clicks on an article, an overlay is displayed with more detailed information about the news.
 * 
 * @function
 * @param {Object} props - Properties passed to the component.
 * @param {Object} props.user - The current logged-in user.
 * @returns {JSX.Element} The rendered news content.
 */
function News(props) {

  /**
   * news - Array of news articles.
   *  @type {Array<Object>} 
  */
  const [news, setNews] = useState([]);

  /** 
   * selectedNews - The currently selected news article for detailed view.
   * @type {Object|null} 
  */
  const [selectedNews, setSelectedNews] = useState(null);
  

  /**
   * Fetches the news articles from the backend API when the component mounts.
   */
  useEffect(() => {
    const jwtToken = localStorage.getItem('token');
    axios.get(`${process.env.REACT_APP_API_URL}/news/findAll`,
      {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
      }
    )
    .then((response) => {
      setNews(response.data);
    })
    .catch((error) => {
    });
  }, []);
  
  /**
   * Opens the news overlay to display detailed information.
   * @param {Object} news - The selected news article.
   */
  const openOverlay = (news) => {
    setSelectedNews(news);
  };

  /**
   * Closes the news overlay.
   */
  const closeOverlay = () => {
    setSelectedNews(null);
  };


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
        <div className='news-content'>
        <h1>NEWS</h1>
<div className='news-container'>

<div  className='article-row'>
          {news.map((news) => (
            <Article key={news.id} news={news} onClick={openOverlay} />
          ))}
          </div>
          
</div>
{selectedNews && (
          <NewsOverlay news={selectedNews} onClose={closeOverlay} />
        )}
        </div>
        </div>
    )
  }
  else{
    return(
      <div className='news-content'>
        <h1>NEWS</h1>
      <div className='news-container'>
      <div  className='article-row'>
                {news.map((news) => (
                  <Article key={news.id} news={news} onClick={openOverlay} />
                ))}
                </div>
                
      </div>
      {selectedNews && (
                <NewsOverlay news={selectedNews} onClose={closeOverlay} />
              )}
              </div>
    )
  }
}

export default News