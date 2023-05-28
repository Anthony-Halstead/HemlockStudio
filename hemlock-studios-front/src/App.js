import { Route, Routes } from 'react-router';
import  {useState, useEffect} from 'react'
import Home from './components/pages/Home';
import Store from './components/pages/Store';
import About from './components/pages/About';
import PageWrapper from './components/reusables/PageWrapper';
import jwt_decode from 'jwt-decode';
import SignIn from './components/pages/SignIn';
import SignUp from './components/pages/SignUp';
import Checkout from './components/pages/Checkout';
import Account from './components/pages/Account';
import Favorites from './components/reusables/Favorites';
import Admin from './components/pages/Admin';
import News from './components/pages/News';


function App() {

  const [user, setUser] = useState({
    id: undefined,
    username: "",
    email: "",
    roles: [],
});
  
useEffect(() => {
  let jwtToken = localStorage.getItem("token");
  if (jwtToken) {
    const decodedToken = jwt_decode(jwtToken);
    const updatedUser = {
      id: decodedToken.userId,
      username: decodedToken.sub,
      email: decodedToken.email,
      roles: decodedToken.roles
    };
    setUser(updatedUser);
  }
}, []);

  return (
    <PageWrapper
    user = {user}
    setUser = {setUser}
    >
      <Routes>
        <Route path="/" element={<Home user={user} setUser={setUser}/>} />
        <Route path="/store" element={<Store user={user} setUser={setUser}/>} />
        <Route path="/about" element={<About/>} />
        <Route path="/news" element={<News />} />
        <Route path="/SignIn" element={<SignIn user={user} setUser={setUser}/>} />
        <Route path="/SignUp" element={<SignUp user={user} setUser={setUser}/>} />
        <Route path="/Account" element={<Account user={user} setUser={setUser}/>} />
        <Route path="/Checkout" element={<Checkout user={user} setUser={setUser}/>} />
        <Route path="/Favorites" element={<Favorites user={user} setUser={setUser}/>} />
        <Route path="/Admin" element={<Admin user={user} setUser={setUser}/>} />
      </Routes>
    </PageWrapper>
  );
}

export default App;