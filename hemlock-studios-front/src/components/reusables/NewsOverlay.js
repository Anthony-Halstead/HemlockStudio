import React from 'react'

function NewsOverlay({ news, onClose }) {

    let style = {};
    let bgClass = 'article-popout-container';
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