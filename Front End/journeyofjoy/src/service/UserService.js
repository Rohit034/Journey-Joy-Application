import axios from 'axios';

const baseurl = "https://localhost:8443/users";
const token = localStorage.getItem('token'); // Get the token from localStorage

class UserService {
    // Sign in the user
    userSignIn(authDTO) {
        return axios.post(`${baseurl}/signin`, authDTO);
    }

    // Register a new user
    userRegistration(userRespDTO) {
        return axios.post(`${baseurl}/userRegistration`, userRespDTO);
    }

    // Get hotels by destination ID
    getHotelsByDestinationId(destinationId) {
        return axios.get(`${baseurl}/gethotels`, {
            headers: {
                'Authorization': `Bearer ${token}`
            },
            params: { destinationid: destinationId }
        });
    }

    // Book a tour
    bookTour(tourDTO, hotelId) {
        return axios.post(`${baseurl}/createtour?hotelId=${hotelId}`, tourDTO, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }

    // Search for destinations by location
    searchDestination(location) {
        return axios.get(`${baseurl}/searchDestination`, {
            headers: {
                'Authorization': `Bearer ${token}`
            },
            params: { Location: location }
        });
    }

    // Add a review for a tour
    addTourReview(reviewDTO, tourId) {
        return axios.post(`${baseurl}/addTourReview?tourId=${tourId}`, reviewDTO, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }

    // Make a booking
    makeBooking(bookingDTO) {
        return axios.post(`${baseurl}/makeBooking`, bookingDTO, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }

    // Make a payment
    makePayment(paymentDTO, bookingId) {
        return axios.post(`${baseurl}/makePayment?bookingId=${bookingId}`, paymentDTO, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });
    }

    // Reset password
    resetPassword(email, newPassword) {
        return axios.post(`${baseurl}/resetPassword`, null, {
            headers: {
                'Authorization': `Bearer ${token}`
            },
            params: { email, newPassword }
        });
    }
}

export default new UserService();
