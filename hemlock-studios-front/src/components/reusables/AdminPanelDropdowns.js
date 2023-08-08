/**
 * @module AdminPanelDropdown
 */

/**
 * Contains styling information for the admin panel dropdown.
 */
import '../../css/reusables/adminpanel.css';

/**
 * AdminPanelDropdown provides a dropdown menu for admin operations.
 * 
 * This component renders a list of admin operation options passed to it as props. When an option is clicked,
 * it triggers a callback, `onOptionClick`, also passed as a prop, with the selected option.
 * 
 * @function
 * 
 * @param {Object} props - Properties passed to the component.
 * @param {Array<string>} props.options - List of admin operation options to display.
 * @param {function(string): void} props.onOptionClick - Callback to execute when an option is clicked.
 * 
 * @returns {JSX.Element} Rendered AdminPanelDropdown component.
 */
function AdminPanelDropdown({options, onOptionClick}) {
    return (
      <div className='admin-panel-dropdown'>
        <ul className='admin-panel-dropdown'>
          {options.map(option => (
            <li><a onClick={() => onOptionClick(option)}>{option}</a></li>
          ))}
        </ul>
      </div>
    );
  }
export default AdminPanelDropdown