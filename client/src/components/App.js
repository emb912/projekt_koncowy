import '../styles/App.css';
import AppContent from './AppContent';
import PricesData from './data/PricesData.js';
import Navbar from './view/Navbar.js';
import Header from './view/Header';
import LoginForm from './auth/LoginForm.js';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import WelcomeContent from './WelcomeContent.js';
import React, { useState } from 'react';

function App() {

  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('token'));

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    localStorage.removeItem('token');
  };

  return (

    <Router>
      <div class="container-fluid" >
        <Header pageTitle="Zestawienie danych na temat cen mieszkań w okresie ostatnich 10 lat"></Header>
        <Navbar isLoggedIn={isLoggedIn} onLogout={handleLogout} />
        <div className="row">
          <div className="col">
            <Routes>
              <Route path="/" element={<AppContent />} />
              <Route path="/data" element={<PricesData />} />
              <Route path="/login" element={<LoginForm onLogin={handleLogin} />} />
              <Route path="/welcome" element={<WelcomeContent />} />
            </Routes>
          </div>
        </div>
      </div>
    </Router>

  );
}

export default App;
