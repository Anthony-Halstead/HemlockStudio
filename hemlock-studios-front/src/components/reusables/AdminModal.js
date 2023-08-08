/**
 * @module AdminModal
 */

/**
 * UpdateUsers component displays the interface to modify users.
 * @see {@link ./UpdateUsers}
 */
import UpdateUsers from "./UpdateUsers";

/**
 * Analytics component provides insights into various platform metrics.
 * @see {@link ./Analytics}
 */
import Analytics from "./Analytics";

/**
 * AddNews component provides the ability to add new news items.
 * @see {@link ./AddNews}
 */
import AddNews from "./AddNews";

/**
 * UpdateNews component provides the interface to modify existing news items.
 * @see {@link ./UpdateNews}
 */
import UpdateNews from "./UpdateNews";

/**
 * Contains styling information for the admin panel.
 */
import "../../css/reusables/adminpanel.css";

/**
 * AddAdmin component provides the interface to add administrators.
 * @see {@link ./AddAdmin}
 */
import AddAdmin from "./AddAdmin";

/**
 * The AdminModal component serves as a container for various administrative tasks 
 * based on the provided option. Depending on the `option` prop, it renders the 
 * corresponding admin component.
 * 
 * @function
 * @param {Object} props - Properties passed to the component.
 * @param {string} props.option - Specifies the admin operation to be displayed in the modal.
 * @returns {JSX.Element|null} The corresponding admin component based on the option or null if no matching option.
 */

function AdminModal({ option }) {
    if (option === 'View Analytics') {
        return <Analytics />;
    }
    else if (option === 'Add Admin') {
        return <AddAdmin />;
    }
    else if (option === 'Update Users') {
        return <UpdateUsers />;
    }
    else if (option === 'Add News') {
        return <AddNews />;
    }
    else if (option === 'Update News') {
        return <UpdateNews />;
    }
    return null;
}
export default AdminModal