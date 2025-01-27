import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import logo from "../images/LogoJ.png";
const Header = () => {
  return (
    <header>
      <nav className="navbar navbar-light" >
        <div className="container-fluid">
          <Link className="navbar-brand text-white" to="/">
            
            <img
                src={logo} // Replace with the actual path to your logo
                alt="Logo"
                style={{ maxWidth: '100%',    // Ensures the logo scales proportionally
                  height: 'auto',      // Maintains the aspect ratio
                  maxHeight: '100px',  // Limits the height for responsiveness
                  marginRight: '10px', }}
                
              />
          </Link>
        </div>
      </nav>
    </header>
  );
};

export default Header;
