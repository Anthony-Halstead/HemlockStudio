import UpdateUsers from "./UpdateUsers";
import Analytics from "./Analytics"
import AddNews from "./AddNews";
import UpdateNews from "./UpdateNews";
import "../../css/reusables/adminpanel.css"
import AddAdmin from "./AddAdmin";


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