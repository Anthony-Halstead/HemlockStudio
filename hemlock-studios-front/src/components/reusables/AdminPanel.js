import React, { useState, useEffect } from 'react';
import '../../css/reusables/adminpanel.css'
import AdminPanelDropdown from './AdminPanelDropdowns'
import AdminModal from './AdminModal';

function AdminPanel() {

  const [selectedOption, setSelectedOption] = useState(null);
  const [isScrolling, setScrolling] = useState(false);

  const checkScroll = () => {
      if (window.pageYOffset === 0) {
          setScrolling(false);
      } else {
          setScrolling(true);
      }
  };

  useEffect(() => {
      window.addEventListener('scroll', checkScroll);
      return () => window.removeEventListener('scroll', checkScroll);
  }, []);



  return (
    <div className={`${isScrolling ? 'scrolling' : ''}`}>
    <div>  
      <div className='admin-panel-content'>
        <div className='admin-panel-header' onClick={() => setSelectedOption(null)}  >
          ADMIN PANEL
        </div>
        <div className='admin-elements'>
          <div className='admin-element-text'>Other</div>
          <AdminPanelDropdown
            options={["View Analytics", "Create Coupon"]}
            onOptionClick={setSelectedOption}
          />
        </div>
        <div className='admin-elements'>
          <div className='admin-element-text'>Manage Users</div>
          <AdminPanelDropdown
            options={["Add User", "Update Users"]}
            onOptionClick={setSelectedOption}
          />
        </div>
        <div className='admin-elements'>
          <div className='admin-element-text'>Manage Products</div>
          <AdminPanelDropdown options={["Add Product", "Update Products"]}
            onOptionClick={setSelectedOption} />

        </div>
        <div className='admin-elements'>
          <div className='admin-element-text'>Manage News</div>
          <AdminPanelDropdown options={["Add News", "Update News"]}
            onOptionClick={setSelectedOption} />
        </div>
      </div>
      <div className='display-modal'>
        {selectedOption && <AdminModal option={selectedOption} />}
      </div>
    </div>
    </div>
  )
}

export default AdminPanel