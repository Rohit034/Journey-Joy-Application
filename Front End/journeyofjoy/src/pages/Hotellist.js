import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { FiStar } from "react-icons/fi";
import UserService from "../service/UserService"; // Import your UserService module

const HotelList = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { hotels = [], startDate, endDate, packageType, location: searchLocation } = location.state || {};

  // Ensure we have the necessary data
  if (!startDate || !endDate || !packageType) {
    return <p>Please provide valid search criteria.</p>;
  }

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

  const handleBookTour = async (hotel) => {
    const userId = localStorage.getItem("userId");

    if (!userId) {
      alert("Please log in to book a tour.");
      return;
    }

    const tourData = {
      userId: userId,
      startDate,
      endDate,
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
      console.error("Error booking tour:", error);
      alert("Failed to book the tour.");
    }
  };

  return (
    <div className="container py-5">
      <h1 className="text-center mb-4">Available Hotels for {searchLocation}</h1>
      <div className="row">
        {hotels && hotels.length > 0 ? (
          hotels.map((hotel) => (
            <div className="col-md-4 mb-4" key={hotel.id}>
              <div className="card shadow-lg border-light rounded">
                {/* <img
                  src={hotel.imageUrl || "default-image-url.jpg"} // Add a default image URL if no hotel image exists
                  alt={hotel.name}
                  className="card-img-top"
                  style={{ height: "200px", objectFit: "cover" }}
                /> */}
                <div className="card-body">
                  <h5 className="card-title">{hotel.name}</h5>
                  <p className="card-text">Rating: <FiStar /> {hotel.starRating}</p>
                  <p className="card-text">
                    <strong>Price:</strong> ${calculatePrice(hotel).toFixed(2)}
                  </p>
                  <button
                    className="btn btn-success w-100"
                    onClick={() => handleBookTour(hotel)}
                  >
                    Book Tour
                  </button>
                </div>
              </div>
            </div>
          ))
        ) : (
          <p className="text-center col-12">No hotels found for the selected criteria.</p>
        )}
      </div>
    </div>
  );
};

export default HotelList;
