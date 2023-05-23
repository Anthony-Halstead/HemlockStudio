import './App.css';
import { Route, Routes } from 'react-router';
import Home from './components/pages/Home';
import Store from './components/pages/Store';
import About from './components/pages/About';
import PageWrapper from './components/pages/reusables/PageWrapper';

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
    <PageWrapper>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/store" element={<Store/>} />
        <Route path="/about" element={<About/>} />
      </Routes>
    </PageWrapper>
  );
}

export default App;