import React from 'react';
import Header from './Header';

function PageWrapper(props) {
  return (
    <div>
      <Header user={props.user} setUser={props.setUser} setUpdateUser={props.setUpdateUser}/>
      <div>{props.children}</div> 
    </div>
  )
}
export default PageWrapper;