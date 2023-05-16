import './App.css';
import { Route, Routes } from 'react-router';
import Home from './components/pages/Home';
import Store from './components/pages/Store';
import About from './components/pages/About';
import PageWrapper from './components/pages/reusables/PageWrapper';

function App() {
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