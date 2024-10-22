import axios from 'axios';

var baseUrl="http://localhost:8443/Admin";
const token = localStorage.getItem('token');

class AdminService{
    listAllTour(){
        return axios.get(`${baseUrl}/getAllTour`,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }


    // Destination Management
    listAllDestination(){
        return axios.get(`${baseUrl}/getAllDestination`,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }
    addNewDestination(DestinationDTO){
        return axios.post(`${baseUrl}/newDestination`, DestinationDTO,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }
    deleteDestination(DeleteDestinationid){
        return axios.delete(`${baseUrl}/deletedestination/${DeleteDestinationid}`, DeleteDestinationid,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }
    getDestinationByid(id){
        return axios.get(`${baseUrl}/getDestinationById/${id}`,id,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }
    updateDestination(id,DestinationDTO){
        return axios.put(`${baseUrl}/${id}`, DestinationDTO,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }


// Hotel Management
    addNewhotel(HotelDTO){
        return axios.post(`${baseUrl}/newHotel`, HotelDTO,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }

    listAllHotels(){
        return axios.get(`${baseUrl}/allhotel`,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }
    
    deleteHotelDetails(hotelId){
        return axios.delete(`${baseUrl}/deletehotel/${hotelId}`, hotelId,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }
    getHotelByid(id){
        return axios.get(`${baseUrl}/getHotelById/${id}`,id,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }
    updateHotelDetails(id,HotelDTO){
        return axios.put(`${baseUrl}/Hotel/${id}`, HotelDTO,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }

// Booking

    getallbooking(){
        return axios.get(`${baseUrl}/allBoking`,{headers: {
            'Authorization': `Bearer ${token}`
        }});
    }

//  Review 
getallReviews(){
    return axios.get(`${baseUrl}/allReviews`,{headers: {
        'Authorization': `Bearer ${token}`
    }});
}

// Payment
getallPayments()
{
    return axios.get(`${baseUrl}/allPayments`,{headers: {
        'Authorization': `Bearer ${token}`
    }});

}
}
export default new AdminService();