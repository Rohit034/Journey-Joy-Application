// import React from "react";
// import { motion } from "framer-motion";
// import { FiSearch, FiCalendar, FiUsers, FiMap, FiCompass, FiStar } from "react-icons/fi";
// import Image from "../images/image2.webp"; // Ensure this path is correct

// const HeroSection = () => {
//   const containerVariants = {
//     hidden: { opacity: 0 },
//     visible: {
//       opacity: 1,
//       transition: {
//         staggerChildren: 0.3,
//         duration: 0.8,
//       },
//     },
//   };

//   const itemVariants = {
//     hidden: { x: -50, opacity: 0 },
//     visible: {
//       x: 0,
//       opacity: 1,
//       transition: {
//         duration: 0.8,
//         ease: "easeOut",
//       },
//     },
//   };

//   const styles = {
//     bgGradient: {
//       background: "linear-gradient(to bottom right, #4c0099, #3c0078)",
//     },
//     bgGradientDark: {
//       background: "linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.2))",
//     },
//     textGradient: {
//       background: "linear-gradient(to right, #00c896, #007bff)",
//       WebkitBackgroundClip: "text",
//       color: "transparent",
//     },
//   };

//   return (
//     <div
//       style={{ ...styles.bgGradient, overflow: "hidden" }}
//       className="position-relative min-vh-100 bg-gradient"
//     >
//       {/* Background Image with Overlay */}
//       <motion.div
//         initial={{ scale: 1.1 }}
//         animate={{ scale: 1 }}
//         transition={{ duration: 20, repeat: Infinity, repeatType: "reverse" }}
//         className="position-absolute w-100 h-100"
//         style={{
//           overflow: "hidden", // Prevents content overflow
//           top: 0,
//           left: 0,
//         }}
//       >
//         <img
//           src={Image}
//           alt="Scenic mountain aurora"
//           className="w-100 h-100 object-cover"
//           style={{ mixBlendMode: "overlay", opacity: 0.6 }}
//         />
//         <div
//           className="position-absolute top-0 start-0 w-100 h-100"
//           style={styles.bgGradientDark}
//         ></div>
//       </motion.div>

//       {/* Main Content */}
//       <motion.div
//         variants={containerVariants}
//         initial="hidden"
//         animate="visible"
//         className="container position-relative mt-5 z-index-10 text-white py-5"
//       >
//         {/* Heading Section */}
//         <motion.div className="mb-4 pt-5">
//           <motion.span
//             variants={itemVariants}
//             className="text-success fw-semibold mb-3 pt-5 d-inline-block"
//           >
//             EXPLORE THE WORLD
//           </motion.span>
//           <motion.h1
//             variants={itemVariants}
//             className="display-4 fw-bold text-light"
//             style={styles.textGradient}
//           >
//             Journey Beyond <br />
//             <span className="text-gradient">The Ordinary</span>
//           </motion.h1>
//         </motion.div>

//         {/* Search Form */}
//         <motion.div
//           variants={itemVariants}
//           className="bg-white bg-opacity-10 p-4 rounded shadow border border-light"
//         >
//           <div className="row g-3">
//             {/* Destination Input */}
//             <div className="col-md-4 position-relative">
//               <FiCompass className="position-absolute top-50 start-0 translate-middle-y ms-3 text-success" />
//               <input
//                 type="text"
//                 placeholder="Destination"
//                 className="form-control ps-5"
//               />
//             </div>
//             {/* Date Picker */}
//             <div className="col-md-4 position-relative">
//               <FiCalendar className="position-absolute top-50 start-0 translate-middle-y ms-3 text-success" />
//               <input
//                 type="date"
//                 className="form-control ps-5"
//               />
//             </div>
//             {/* Travelers Dropdown */}
//             <div className="col-md-3 position-relative">
//               <FiUsers className="position-absolute top-50 start-0 translate-middle-y ms-3 text-success" />
//               <select className="form-select ps-5">
//                 <option value="1">1 Traveler</option>
//                 <option value="2">2 Travelers</option>
//                 <option value="3">3 Travelers</option>
//                 <option value="4">4+ Travelers</option>
//               </select>
//             </div>
//             {/* Search Button */}
//             <div className="col-md-1">
//               <button className="btn btn-success w-100 d-flex align-items-center justify-content-center gap-2">
//                 <FiSearch />
//                 Discover
//               </button>
//             </div>
//           </div>
//         </motion.div>

//         {/* Button Section */}
//         <motion.div
//           variants={itemVariants}
//           className="d-flex flex-wrap gap-3 mt-4"
//         >
//           {/* View Destinations Button */}
//           <motion.button
//             whileHover={{ scale: 1.05 }}
//             whileTap={{ scale: 0.95 }}
//             className="btn btn-outline-light d-flex align-items-center gap-2"
//           >
//             <FiMap />
//             View Destinations
//           </motion.button>
//           {/* Top Experiences Button */}
//           <motion.button
//             whileHover={{ scale: 1.05 }}
//             whileTap={{ scale: 0.95 }}
//             className="btn btn-outline-light d-flex align-items-center gap-2"
//           >
//             <FiStar />
//             Top Experiences
//           </motion.button>
//         </motion.div>
//       </motion.div>
//     </div>
//   );
// };

// export default HeroSection;


import React, { useState } from "react";
import { motion } from "framer-motion";
import { FiSearch, FiCalendar, FiUsers, FiMap, FiCompass, FiStar } from "react-icons/fi";
import Image from "../images/image2.webp"; // Ensure this path is correct
import { useNavigate } from "react-router-dom";
import UserService from "../service/UserService"; // Import your UserService module

const HeroSection = () => {
  const [location, setLocation] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [packageType, setPackageType] = useState("");
  const [hotels, setHotels] = useState([]);
  const [searchPerformed, setSearchPerformed] = useState(false);
  const navigate = useNavigate();

  const getPriceBasedOnPackage = (packageType) => {
    switch (packageType) {
      case "ADVENTURE":
        return 150.0;
      case "CULTURAL":
        return 180.0;
      case "CRUISE":
        return 250.0;
      case "FAMILY":
        return 120.0;
      case "ROMANTIC":
        return 220.0;
      case "LUXURY":
        return 300.0;
      default:
        return 0.0;
    }
  };

  const calculatePrice = (hotel) => {
    const days = (new Date(endDate) - new Date(startDate)) / (1000 * 3600 * 24);
    const basePriceForPackage = getPriceBasedOnPackage(packageType);
    const hotelPriceByRating = hotel.starRating * 50.0;
    return basePriceForPackage + hotelPriceByRating * days;
  };

  const handleSearch = async (e) => {
    e.preventDefault();
    try {
      const destinationResponse = await UserService.searchDestination(location);
      const destinationId = destinationResponse.data[0].id;
      const hotelResponse = await UserService.getHotelsByDestinationId(destinationId);
      setHotels(hotelResponse.data);
      setSearchPerformed(true);
    } catch (error) {
      console.error("Error fetching hotels:", error);
      setSearchPerformed(false);
    }
  };

  const handleBookTour = async (hotel) => {
    const userId = localStorage.getItem("userId");

    if (!userId) {
      alert("Please log in to book a tour.");
      return;
    }

    const tourData = {
      userId: userId,
      startDate: startDate,
      endDate: endDate,
      packages: packageType,
      hotelId: hotel.id,
      price: calculatePrice(hotel),
    };
    localStorage.setItem("tourData", JSON.stringify(tourData));

    try {
      const result = await UserService.bookTour(tourData, hotel.id);
      const tour = result.data;
      localStorage.setItem("tourId", tour.id);
      navigate(`/payment`, { state: { tourData } });
    } catch (error) {
      console.error("Error booking tour or creating booking:", error);
      alert("Failed to book the tour or create the booking.");
    }
  };

  const styles = {
    bgGradient: {
      background: "linear-gradient(to bottom right, #4c0099, #3c0078)",
    },
    bgGradientDark: {
      background: "linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.2))",
    },
    textGradient: {
      background: "linear-gradient(to right, #00c896, #007bff)",
      WebkitBackgroundClip: "text",
      color: "transparent",
    },
  };

  return (
    <div
    style={{ ...styles.bgGradient, overflow: "hidden", position: "relative", minHeight: "100vh" }}
    className="bg-gradient"
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
        src={Image}
        alt="Scenic mountain aurora"
        className="w-100 h-100 object-cover"
        style={{ mixBlendMode: "overlay", opacity: 0.6 }}
      />
      <div
        className="position-absolute top-0 start-0 w-100 h-100"
        style={styles.bgGradientDark}
      ></div>
    </motion.div>
  
    {/* Main Content */}
<motion.div
  className="container position-absolute d-flex flex-column align-items-center justify-content-center w-100 text-white py-5"
  style={{
    zIndex: 10,
    top: 0,
    bottom: 0,
    left: 0,
    right: 0,
    height: "100vh", // Full height of the viewport
  }}
>
  <motion.h1
    className="display-4 fw-bold text-light text-center"
    style={styles.textGradient}
  >
    Journey Beyond <br />
    <span className="text-gradient">The Ordinary</span>
  </motion.h1>

  {/* Search Form */}
  <form onSubmit={handleSearch} className="bg-white p-4 w-100 rounded shadow border">
    <div className="row g-3">
      <div className="col-md-4">
        <input
          type="text"
          className="form-control"
          placeholder="Enter a destination"
          value={location}
          onChange={(e) => setLocation(e.target.value)}
          required
        />
      </div>
      <div className="col-md-2">
        <select
          className="form-select"
          value={packageType}
          onChange={(e) => setPackageType(e.target.value)}
          required
        >
          <option value="">Package Type</option>
          <option value="ADVENTURE">Adventure</option>
          <option value="CULTURAL">Cultural</option>
          <option value="CRUISE">Cruise</option>
          <option value="FAMILY">Family</option>
          <option value="ROMANTIC">Romantic</option>
          <option value="LUXURY">Luxury</option>
        </select>
      </div>
      <div className="col-md-2">
        <input
          type="date"
          className="form-control"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
          required
        />
      </div>
      <div className="col-md-2">
        <input
          type="date"
          className="form-control"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}
          required
        />
      </div>
      <div className="col-md-2">
        <button type="submit" className="btn btn-success w-100">
          Search
        </button>
      </div>
    </div>
  </form>
  
      {searchPerformed && (
        <div className="mt-4">
          <h2 className="text-center mb-4">Available Hotels</h2>
          <div className="row">
            {hotels.length > 0 ? (
              hotels.map((hotel) => (
                <div className="col-md-4 mb-4" key={hotel.id}>
                  <div className="card h-100">
                    <div className="card-body">
                      <h5 className="card-title">{hotel.name}</h5>
                      <p>Rating: {hotel.starRating}</p>
                      <p>Price: ${calculatePrice(hotel).toFixed(2)}</p>
                      <button
                        className="btn btn-success mt-3"
                        onClick={() => handleBookTour(hotel)}
                      >
                        Book Tour
                      </button>
                    </div>
                  </div>
                </div>
              ))
            ) : (
              <p>No hotels found for the selected criteria.</p>
            )}
          </div>
        </div>
      )}
    </motion.div>
  </div>
  
  );
};

export default HeroSection;
