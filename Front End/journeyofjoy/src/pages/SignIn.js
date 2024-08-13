import React, { useState } from 'react';
import UserService from '../service/UserService'
import { useNavigate } from 'react-router-dom';


const SignIn = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSignIn = (e) => {
    e.preventDefault();
    const authDTO = { email, password };

    UserService.userSignIn(authDTO)
      .then((response) => {
        console.log('User signed in successfully:', response.data);
        navigate('/TourList');
      }) 
      .catch((error) => {
        console.error('There was an error signing in!', error);
      });
  };

  return (
    <div>
      <h2>Sign In</h2>
      <form onSubmit={handleSignIn}>
        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <button type="submit">Sign In</button>
      </form>
    </div>
  );
};

export default SignIn;