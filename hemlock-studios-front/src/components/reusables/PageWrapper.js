import React from 'react';
import Header from './Header';



function PageWrapper({ children }) {
  return (
    <div>
      <Header />
      <div>{children}</div>
    </div>
  )
}

export default PageWrapper;