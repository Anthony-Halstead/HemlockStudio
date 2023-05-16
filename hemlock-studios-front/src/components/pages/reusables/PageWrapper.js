import React from 'react';
import Header from './Header';
import Footer from './Footer';
import '../../../css/reusables/pagewrapper.css';


function PageWrapper({ children }) {
  return (
    <div>
      <Header />
      <div>{children}</div>
    </div>
  )
}

export default PageWrapper;