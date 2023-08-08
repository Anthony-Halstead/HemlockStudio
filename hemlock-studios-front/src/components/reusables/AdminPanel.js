/**
 * @module AdminPanel
 */

/**
 * Contains styling information for the admin panel.
 */
import '../../css/reusables/adminpanel.css';
import React, { useState, useEffect} from 'react';
/**
 * AdminPanelDropdown provides a dropdown UI for various admin operations.
 * @see {@link ./AdminPanelDropdowns}
 */
import AdminPanelDropdown from './AdminPanelDropdowns';

/**
 * AdminModal is a component that renders specific administrative actions based on the selected option.
 * @see {@link ./AdminModal}
 */
import AdminModal from './AdminModal';

/**
 * AdminPanel is a primary interface for administrators. It offers a navigation
 * panel with dropdowns for various administrative operations. Depending on the selected option,
 * it can render different admin modals for tasks like viewing analytics, managing users, products, news, etc.
 * 
 * The component also responds to the scroll event, adjusting its styling accordingly.
 * 
 * @function
 * @returns {JSX.Element} Rendered AdminPanel component.
 */
function AdminPanel() {

  /** The currently selected admin operation. */
  const [selectedOption, setSelectedOption] = useState(null);

  /** A boolean indicating if the user is currently scrolling the page. */
  const [isScrolling, setScrolling] = useState(false);

  /** 
   * Checks if the user is at the top of the page.
   * Updates the `isScrolling` state accordingly.
   */
  const checkScroll = () => {
      if (window.pageYOffset === 0) {
          setScrolling(false);
      } else {
          setScrolling(true);
      }
  };

  /**
   * Attaches a scroll listener when the component mounts, 
   * and detaches it when the component unmounts.
   */
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
            options={["View Analytics", "Create Coupon", "Update Coupon"]}
            onOptionClick={setSelectedOption}
          />
        </div>
        <div className='admin-elements'>
          <div className='admin-element-text'>Manage Users</div>
          <AdminPanelDropdown
            options={["Add Admin", "Update Users"]}
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