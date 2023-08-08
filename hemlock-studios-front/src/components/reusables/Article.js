/**
 * Article module.
 * 
 * This module provides the Article component, which displays a news article
 * and optionally sets a background image for the article if provided.
 * 
 * @module Article
 */

/**
 * React's default import. Required to define and use React components.
 * 
 * @see {@link https://reactjs.org/|React Official Documentation}
 * @external React
 */
import React from 'react';

/**
 * Article Component.
 * 
 * Represents a single news article. If the article has associated images,
 * the first image in the list is used as a background for the article.
 * When clicked, the entire article content is sent via the onClick callback.
 * 
 * @param {Object} props - Component properties.
 * @param {Object} props.news - News article data.
 * @param {string} props.news.anouncement - The announcement associated with the article.
 * @param {string} props.news.title - Title of the article.
 * @param {Object[]} [props.news.photoReal=[]] - List of photos associated with the article. 
 * @param {string} props.news.photoReal[].photoUrl - URL of a photo.
 * @param {Function} props.onClick - Callback function to handle click event on the article.
 * @returns {React.Element} Rendered Article component.
 */
function Article({ news, onClick }) {
    /**
     * Handler for the click event on the article.
     * Invokes the onClick callback with the news article data.
     */
    const handleClick = () => {
        onClick(news);
    };

    /**
     * Styles and classes applied to the article container.
     */
    let style = {};
    let bgClass = 'article-container';

    /**
     * If the article has photos associated with it,
     * use the first photo as a background image for the article.
     */
    if (news.photoReal.length > 0) {
      style = { backgroundImage: `url(${news.photoReal[0].photoUrl})` };
      bgClass += ' article-container-with-bg';
    }


    return (
        <div className={bgClass} style={style} onClick={handleClick}>
            <div className="article-content">
                <h2 className='announcement'>{news.anouncement}</h2>
                <div >
                    <h1>{news.title}</h1>
                </div>
            </div>
        </div>
    );
}

export default Article;