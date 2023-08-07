/**
 * @typedef {Object} User
 * @property {number} id - The ID of the user.
 * @property {string} username - The username of the user.
 * @property {string} email - The email of the user.
 * @property {Array<string>} roles - The roles of the user.
 */

/**
 * The main application component that sets up routes and renders pages based on the current URL.
 * Manages user authentication and updating user data.
 * 
 * @function
 * @returns {JSX.Element} A JSX element wrapped in a ToastProvider and PageWrapper.
 */
import { Route, Routes } from 'react-router';
import  {useState, useEffect} from 'react'
import Home from './components/pages/Home';
import About from './components/pages/About';
import PageWrapper from './components/reusables/PageWrapper';
import SignIn from './components/pages/SignIn';
import SignUp from './components/pages/SignUp';
import Account from './components/pages/Account';
import News from './components/pages/News';
import DropdownMenu from './components/reusables/DropdownMenu';
import axios from 'axios';
import { ToastProvider } from '../src/components/reusables/ToastContext';
import Toast from '../src/components/reusables/Toast'

/**
 * The App component. 
 * Manages routes for the application and provides user authentication.
 * @function
 * @returns {JSX.Element} - The rendered component.
 */
function App() {

  /**
   * Represents the user's data.
   * @name userDataState
   * @type {User}
   */
  const [user, setUser] = useState({
    id: undefined,
    username: "",
    email: "",
    roles: [],
  });

  /**
   * Used to trigger a re-render of the component when updated.
   * Especially useful for updating user data or triggering other actions in the component.
   * 
   * @name updateUserState
   * @type {Object}
   */
  const [updateUser, setUpdateUser] = useState({});

  /**
   * @name fetchUserDataEffect
   * @function
   * Fetches user data from the server and updates the user state variable.
   */
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
      <PageWrapper user={user} setUser={setUser} setUpdateUser={setUpdateUser}>
        <Toast />
        <Routes>
          <Route path="/" element={<Home user={user} setUser={setUser} />} />
          <Route path="/about" element={<About user={user} setUser={setUser} />} />
          <Route path="/news" element={<News user={user} setUser={setUser} />} />
          <Route path="/SignIn" element={<SignIn user={user} setUser={setUser} />} />
          <Route path="/SignUp" element={<SignUp user={user} setUser={setUser} />} />
          <Route path="/Account" element={<Account user={user} setUser={setUser} setUpdateUser={setUpdateUser} />} />
          <Route path="/DropDownMenu" element={<DropdownMenu user={user} setUser={setUser} setUpdateUser={setUpdateUser} />} />
        </Routes>
      </PageWrapper>
    </ToastProvider>
  );
}

export default App;
