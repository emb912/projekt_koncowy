import * as React from 'react';
import '../../styles/App.css';
export default function Buttons({ login, logout }) {

  const token = localStorage.getItem("token");

  return (
    <>
      {!token ? (
        <a href='/login'>
          <button className="btn btn-primary" onClick={login}>
            Uzyskaj dostęp
          </button>
        </a>

      ) : (
        <a href='/'>
          <button className="btn btn-dark" onClick={logout}>
            Wyloguj się
          </button>
        </a>
      )}
    </>
  );
};
