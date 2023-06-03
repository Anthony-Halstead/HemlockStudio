import React from 'react';
import Header from './Header';



function PageWrapper(props) {
  return (
    <div>
      
      <Header props={props}/>
      <div>{props.children}</div>
      
    </div>
  )
}

export default PageWrapper;