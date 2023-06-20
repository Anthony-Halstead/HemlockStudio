import React, { useEffect, useState } from 'react'
import AdminPanel from '../reusables/AdminPanel'
import Draggable from 'react-draggable'
import '../../css/pages/news.css'
import axios from 'axios';
import Article from '../reusables/Article';
import ProductOverlay from '../reusables/ProductOverlay';
import NewsOverlay from '../reusables/NewsOverlay';

function News(props) {

  const [news, setNews] = useState([]);
  const [selectedNews, setSelectedNews] = useState(null);
  

  useEffect(() => {
    let jwtToken = localStorage.getItem('token');
    axios.get("https://3.16.219.108:8080/news/findAll",
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
        console.error('Error fetching news', error);
      });
  }, []);

  
  const openOverlay = (news) => {
    setSelectedNews(news);
  };

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