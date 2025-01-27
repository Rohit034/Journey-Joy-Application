// import React, { useState } from 'react';
// import UserService from '../service/UserService';
// import 'bootstrap/dist/css/bootstrap.min.css';

// const ResetPassword = () => {
//     const [email, setEmail] = useState('');
//     const [newPassword, setNewPassword] = useState('');
//     const [message, setMessage] = useState('');

//     const handleResetPassword = async (e) => {
//         e.preventDefault();
//         try {
//             const response = await UserService.resetPassword(email, newPassword);
//             setMessage(response.data.message);
//         } catch (error) {
//             setMessage(error.response?.data?.message || 'An error occurred');
//         }
//     };

//     return (
//         <div className="d-flex align-items-center justify-content-center vh-100 bg-light">
//             <div className="card shadow p-4" style={{ maxWidth: '400px', width: '100%' }}>
//                 <div className="text-center mb-4">
//                     <img
//                         alt="Your Company"
//                         src="https://img.icons8.com/?size=100&id=nMSSSpYre8pz&format=png&color=000000"
//                         className="mb-4"
//                         style={{ height: '40px' }}
//                     />
//                     <h2 className="h4 mb-3 font-weight-normal">Reset Your Password</h2>
//                 </div>
//                 <form onSubmit={handleResetPassword}>
//                     <div className="form-group">
//                         <label htmlFor="email">Email</label>
//                         <input
//                             type="email"
//                             id="email"
//                             className="form-control"
//                             placeholder="Enter your email"
//                             value={email}
//                             onChange={(e) => setEmail(e.target.value)}
//                             required
//                         />
//                     </div>
//                     <div className="form-group">
//                         <label htmlFor="newPassword">New Password</label>
//                         <input
//                             type="password"
//                             id="newPassword"
//                             className="form-control"
//                             placeholder="Enter your new password"
//                             value={newPassword}
//                             onChange={(e) => setNewPassword(e.target.value)}
//                             required
//                         />
//                     </div>
//                     <button type="submit" className="btn btn-primary btn-block mt-3">
//                         Reset Password
//                     </button>
//                     {message && (
//                         <div className={`mt-3 ${message.startsWith('Error') ? 'text-danger' : 'text-success'}`}>
//                             {message}
//                         </div>
//                     )}
//                 </form>
//             </div>
//         </div>
//     );
// };

// export default ResetPassword;

import React, { useState } from 'react';
import { motion } from 'framer-motion';
import UserService from '../service/UserService';
import 'bootstrap/dist/css/bootstrap.min.css';
import BackgroundImage from '../images/image2.webp'; // Update this to the correct path of your background image

const ResetPassword = () => {
    const [email, setEmail] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [message, setMessage] = useState('');

    const handleResetPassword = async (e) => {
        e.preventDefault();
        try {
            const response = await UserService.resetPassword(email, newPassword);
            setMessage(response.data.message);
        } catch (error) {
            setMessage(error.response?.data?.message || 'An error occurred');
        }
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

            {/* Reset Password Form */}
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
                        <h2 className="h4 mb-3 font-weight-normal">Reset Your Password</h2>
                    </div>
                    <form onSubmit={handleResetPassword}>
                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <input
                                type="email"
                                id="email"
                                className="form-control"
                                placeholder="Enter your email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="newPassword">New Password</label>
                            <input
                                type="password"
                                id="newPassword"
                                className="form-control"
                                placeholder="Enter your new password"
                                value={newPassword}
                                onChange={(e) => setNewPassword(e.target.value)}
                                required
                            />
                        </div>
                        <button type="submit" className="btn btn-primary btn-block mt-3">
                            Reset Password
                        </button>
                        {message && (
                            <div className={`mt-3 ${message.startsWith('Error') ? 'text-danger' : 'text-success'}`}>
                                {message}
                            </div>
                        )}
                    </form>
                </div>
            </div>
        </div>
    );
};

export default ResetPassword;
