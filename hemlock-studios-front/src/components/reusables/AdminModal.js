import AddUser from "./AddUser";
import UpdateUsers from "./UpdateUsers";
import Analytics from "./Analytics"
import AddProduct from "./AddProduct";
import UpdateProducts from "./UpdateProducts";
import AddNews from "./AddNews";
import UpdateNews from "./UpdateNews";
import CreateCoupon from "./CreateCoupon";
import "../../css/reusables/adminpanel.css"

function AdminModal({ option }) {
    if (option === 'View Analytics') {
        return <Analytics />;
    }
    else if(option === 'Create Coupon')
    {
        return <CreateCoupon />;
    }
    else if (option === 'Add User') {
        return <AddUser />;
    }
    else if (option === 'Update Users') {
        return <UpdateUsers />;
    }
    else if (option === 'Add Product') {
        return <AddProduct />;
    }
    else if (option === 'Update Products') {
        return <UpdateProducts />;
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