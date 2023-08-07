import { Route, Routes } from 'react-router';
import  {useState, useEffect} from 'react'
import Home from './components/pages/Home';
import Store from './components/pages/Store';
import About from './components/pages/About';
import PageWrapper from './components/reusables/PageWrapper';
import SignIn from './components/pages/SignIn';
import SignUp from './components/pages/SignUp';
import Checkout from './components/pages/Checkout';
import Account from './components/pages/Account';
import Favorites from './components/reusables/Favorites';
import News from './components/pages/News';
import Cart from './components/reusables/Cart'
import DropdownMenu from './components/reusables/DropdownMenu';
import axios from 'axios';
import { ToastProvider } from '../src/components/reusables/ToastContext';
import Toast from '../src/components/reusables/Toast'
import Receipt from './components/pages/Receipt';


function App() {

  const [user, setUser] = useState({
    id: undefined,
    username: "",
    email: "",
    roles: [],
});


const [updateUser, setUpdateUser] = useState({})
  
useEffect(() => {
  let jwtToken = localStorage.getItem("token");
  if (jwtToken) {
    axios
  .get(`${process.env.REACT_APP_API_URL}/user/getUser`, {
    headers: {
      'Authorization': `Bearer ${jwtToken}`
    }
  })
      .then(response => {
        setUser({
          ...response.data,
          roles: response.data.roles.map(role => role.authority)
        });
      })
      .catch(e => {
        localStorage.removeItem("token");
      });
  }
}, [updateUser]);

return (
  <ToastProvider>
    <PageWrapper
      user={user}
      setUser={setUser}
      setUpdateUser={setUpdateUser}
    >
      <Toast />
      <Routes>
        <Route path="/" element={<Home user={user} setUser={setUser} />} />
        <Route path="/store" element={<Store user={user} setUser={setUser} setUpdateUser={setUpdateUser} />} />
        <Route path="/about" element={<About user={user} setUser={setUser} />} />
        <Route path="/news" element={<News user={user} setUser={setUser} />} />
        <Route path="/SignIn" element={<SignIn user={user} setUser={setUser} />} />
        <Route path="/SignUp" element={<SignUp user={user} setUser={setUser} />} />
        <Route path="/Account" element={<Account user={user} setUser={setUser} setUpdateUser={setUpdateUser} />} />
        <Route path="/Checkout" element={<Checkout user={user} setUser={setUser} setUpdateUser={setUpdateUser} />} />
        <Route path="/Favorites" element={<Favorites user={user} setUser={setUser} setUpdateUser={setUpdateUser} />} />
        <Route path="/DropDownMenu" element={<DropdownMenu user={user} setUser={setUser} setUpdateUser={setUpdateUser} />} />
        <Route path="/Cart" element={<Cart user={user} setUser={setUser} setUpdateUser={setUpdateUser} />} />
        <Route path="/Receipt" element={<Receipt />}/>
      </Routes>
    </PageWrapper>
  </ToastProvider>
);
}

export default App;