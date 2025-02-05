import React from 'react';
import { NavLink } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
// import './MainNavBar.css'; // Optional if you want to use external CSS
import logo from "../images/Logoo.png";
import { Link } from 'react-router-dom';
export default function MainNavBar() {
  return (
    <div>
      <header>
        <nav
          className="navbar navbar-expand-lg navbar-light shadow-sm bg-white fixed-top"
          style={{
            backgroundColor: '#f8f9fa',
            boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
            padding: '0.75rem 1.5rem',
          }}
        >
          <div className="container-fluid">
          <Link className="navbar-brand text-white" to="/">
            
            <img
                src={logo} // Replace with the actual path to your logo
                alt="Logo"
                style={{ maxWidth: '100%',    // Ensures the logo scales proportionally
                  height: 'auto',      // Maintains the aspect ratio
                  maxHeight: '70px',  // Limits the height for responsiveness
                  marginRight: '10px', }}
                
              />
          </Link>
        </div>
          <div className="container-fluid">
            {/* <NavLink
              className="navbar-brand text-black fw-bold"
              to="/"
              style={{ fontSize: '1.5rem', color: '#007bff' }}
            >
              Home
            </NavLink> */}
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav ms-auto">
                <li className="nav-item">
                  <NavLink
                    className="nav-link fw-bold"
                    to="/SignIn"
                    style={{
                      color: '#007bff',
                      fontSize: '1rem',
                      marginRight: '1rem',
                    }}
                  >
                    Sign In
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink
                    className="nav-link fw-bold"
                    to="/logout"
                    style={{
                      color: '#dc3545',
                      fontSize: '1rem',
                    }}
                  >
                    Logout
                  </NavLink>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
    </div>
  );
}
