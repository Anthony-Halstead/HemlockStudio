import React from 'react';
import '../../css/pages/news.css';

function Article({ news, onClick }) {
    const handleClick = () => {
        onClick(news);
    };

    let style = {};
    let bgClass = 'article-container';
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