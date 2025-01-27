// import React, { useState } from 'react';
// import { useNavigate } from 'react-router-dom';
// import UserService from '../service/UserService';
// import 'bootstrap/dist/css/bootstrap.min.css';

// export default function SignInForm() {
//   const [formDetails, setFormDetails] = useState({
//     email: '',
//     password: ''
//   });
//   const [errorMessage, setErrorMessage] = useState('');  // New state for error messages

//   const navigate = useNavigate();

//   const handleChange = (event) => {
//     const name = event.target.name;
//     setFormDetails({
//       ...formDetails,
//       [name]: event.target.value
//     });
//   };

//   const signInUser = () => {
//     if (formDetails.email === '' || formDetails.password === '') {
//       alert('Please fill all the details');
//       return;
//     }

//     UserService.userSignIn(formDetails)
//       .then((result) => {
//         console.log('User signed in successfully:', result);

//         const user = result.data.user;
//         localStorage.setItem('token',result.data.jwt)
//         if (user && user.id) {
//           localStorage.setItem('userId', user.id);
//           console.log('User role:', user.role);
//           if (user.role === 'ROLE_CUSTOMER') {
//             navigate('/TourList');
//           } else if (user.role === 'ROLE_ADMIN') {
//             navigate('/admin/dashboard');
//           } else {
//             alert('Unknown role. Please contact support.');
//           }
//         } else {
//           alert('Failed to retrieve user information.');
//         }
//       })
//       .catch((error) => {
//         console.error('There was an error signing in!', error);
//         // Set the error message based on the response
//         setErrorMessage(error.response?.data?.message || 'Failed to sign in. Please check your credentials and try again.');
//       });
//   };

//   const handleForgotPassword = () => {
//     navigate('/reset-password');
//   };

//   return (
//     <div className="d-flex align-items-center justify-content-center vh-100 bg-light">
//       <div className="card shadow p-4" style={{ maxWidth: '400px', width: '100%' }}>
//         <div className="text-center mb-4">
//           <img
//             alt="Your Company"
//             src="https://img.icons8.com/?size=100&id=nMSSSpYre8pz&format=png&color=000000"
//             className="mb-4"
//             style={{ height: '40px' }}
//           />
//           <h2 className="h4 mb-3 font-weight-normal">Sign in to your account</h2>
//         </div>
//         <form>
//           <div className="form-group">
//             <label htmlFor="email">Email address</label>
//             <input
//               type="email"
//               id="email"
//               name="email"
//               className="form-control"
//               placeholder="Enter email"
//               value={formDetails.email}
//               onChange={handleChange}
//               required
//             />
//           </div>

//           <div className="form-group">
//             <label htmlFor="password">Password</label>
//             <input
//               type="password"
//               id="password"
//               name="password"
//               className="form-control"
//               placeholder="Enter password"
//               value={formDetails.password}
//               onChange={handleChange}
//               required
//             />
//           </div>

//           <button type="button" className="btn btn-primary btn-block mt-3" onClick={signInUser}>
//             Sign In
//           </button>

//           {errorMessage && (
//             <div className="text-danger mt-3">
//               {errorMessage}
//             </div>
//           )}

//           <div className="text-center mt-3">
//             <p>
//               Don't have an account?{' '}
//               <a href="/signup" className="text-decoration-none">
//                 Sign up
//               </a>
//             </p>
//             <p>
//               <a href="#!" onClick={handleForgotPassword} className="text-decoration-none">
//                 Forgot Password?
//               </a>
//             </p>
//           </div>
//         </form>
//       </div>
//     </div>
//   );
// }

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';
import UserService from '../service/UserService';
import 'bootstrap/dist/css/bootstrap.min.css';
import BackgroundImage from '../images/image2.webp'; // Ensure the correct path to your background image

export default function SignInForm() {
  const [formDetails, setFormDetails] = useState({
    email: '',
    password: ''
  });
  const [errorMessage, setErrorMessage] = useState(''); // State for error messages

  const navigate = useNavigate();

  const handleChange = (event) => {
    const name = event.target.name;
    setFormDetails({
      ...formDetails,
      [name]: event.target.value
    });
  };

  const signInUser = () => {
    if (formDetails.email === '' || formDetails.password === '') {
      alert('Please fill all the details');
      return;
    }

    UserService.userSignIn(formDetails)
      .then((result) => {
        console.log('User signed in successfully:', result);

        const user = result.data.user;
        localStorage.setItem('token', result.data.jwt);
        if (user && user.id) {
          localStorage.setItem('userId', user.id);
          console.log('User role:', user.role);
          if (user.role === 'ROLE_CUSTOMER') {
            navigate('/TourList');
          } else if (user.role === 'ROLE_ADMIN') {
            navigate('/admin/dashboard');
          } else {
            alert('Unknown role. Please contact support.');
          }
        } else {
          alert('Failed to retrieve user information.');
        }
      })
      .catch((error) => {
        console.error('There was an error signing in!', error);
        setErrorMessage(
          error.response?.data?.message || 'Failed to sign in. Please check your credentials and try again.'
        );
      });
  };

  const handleForgotPassword = () => {
    navigate('/reset-password');
  };

  const styles = {
    bgGradient: {
      background: "linear-gradient(to bottom right, #4c0099, #3c0078)"
    },
    bgGradientDark: {
      background: "linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.2))"
    }
  };

  return (
    <div
      style={{
        ...styles.bgGradient,
        position: "relative",
        minHeight: "100vh",
        overflow: "hidden"
      }}
    >
      {/* Background Image */}
      <motion.div
        initial={{ scale: 1.1 }}
        animate={{ scale: 1 }}
        transition={{ duration: 20, repeat: Infinity, repeatType: "reverse" }}
        className="position-absolute w-100 h-100"
        style={{ top: 0, left: 0 }}
      >
        <img
          src={BackgroundImage}
          alt="Background"
          className="w-100 h-100 object-cover"
          style={{ mixBlendMode: "overlay", opacity: 0.6 }}
        />
        <div
          className="position-absolute top-0 start-0 w-100 h-100"
          style={styles.bgGradientDark}
        ></div>
      </motion.div>

      {/* Sign In Form */}
      <div
        className="d-flex align-items-center justify-content-center position-relative"
        style={{ zIndex: 10, minHeight: "100vh" }}
      >
        <div className="card shadow p-4" style={{ maxWidth: '400px', width: '100%' }}>
          <div className="text-center mb-4">
            <img
              alt="Your Company"
              src="https://img.icons8.com/?size=100&id=nMSSSpYre8pz&format=png&color=000000"
              className="mb-4"
              style={{ height: '40px' }}
            />
            <h2 className="h4 mb-3 font-weight-normal">Sign in to your account</h2>
          </div>
          <form>
            <div className="form-group">
              <label htmlFor="email">Email address</label>
              <input
                type="email"
                id="email"
                name="email"
                className="form-control"
                placeholder="Enter email"
                value={formDetails.email}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                id="password"
                name="password"
                className="form-control"
                placeholder="Enter password"
                value={formDetails.password}
                onChange={handleChange}
                required
              />
            </div>

            <button type="button" className="btn btn-primary btn-block mt-3" onClick={signInUser}>
              Sign In
            </button>

            {errorMessage && (
              <div className="text-danger mt-3">
                {errorMessage}
              </div>
            )}

            <div className="text-center mt-3">
              <p>
                Don't have an account?{' '}
                <a href="/signup" className="text-decoration-none">
                  Sign up
                </a>
              </p>
              <p>
                <a href="#!" onClick={handleForgotPassword} className="text-decoration-none">
                  Forgot Password?
                </a>
              </p>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
