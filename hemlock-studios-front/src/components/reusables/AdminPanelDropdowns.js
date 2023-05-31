import '../../css/reusables/adminpanel.css' 

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