/**
 * @module NewsOverlay
 */

import React from 'react'

/**
 * NewsOverlay is a functional component for displaying an overlay with news details.
 * 
 * @param {Object} props - Properties passed to the component.
 * @param {Object} props.news - Object containing news details.
 * @param {Array} props.news.photoReal - Array containing photos associated with the news.
 * @param {string} props.news.title - The title of the news article.
 * @param {string} props.news.description - The short description or subtitle of the news article.
 * @param {string} props.news.body - The main content of the news article.
 * @param {function} props.onClose - Function to be called when the overlay is clicked, typically to close the overlay.
 * @returns {JSX.Element} The NewsOverlay component.
 */
function NewsOverlay({ news, onClose }) {

  /**
   * `style` defines the background image style for the news overlay, if a photo exists.
   * `bgClass` determines the CSS class for the overlay background.
   */
    let style = {};
    let bgClass = 'article-popout-container';
    
    /**
     * If the news article has associated photos, the background image style and CSS class are updated.
     */
    if (news.photoReal.length > 0) {
      style = { backgroundImage: `url(${news.photoReal[1].photoUrl})` };
      bgClass += ' article-popout-container-with-bg';
    }

  return (
    <div className="article-popout" onClick={onClose}>
        <div className={bgClass} style={style}>
            <div className='popout-content'>
                    <h1 className='announcement'>{news.title}</h1>
                    <h3>{news.description}</h3>
                    <p>{news.body}</p>
                    </div>
            </div>
        </div>
  );
}

export default NewsOverlay