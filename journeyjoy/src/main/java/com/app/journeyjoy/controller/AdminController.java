package com.app.journeyjoy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.journeyjoy.dto.DestinationDTO;
import com.app.journeyjoy.dto.HotelDTO;
import com.app.journeyjoy.entities.Hotel;
import com.app.journeyjoy.service.DestinationService;
import com.app.journeyjoy.service.HotelService;



@RestController
@RequestMapping("/Admin")
public class AdminController {
	
	@Autowired // byType
	private HotelService hotelservice;
	@Autowired
	private DestinationService destinationService;


	@GetMapping("/allhotel")
	public List<HotelDTO> listAllHotels() {
		System.out.println("in list all categories");
		return hotelservice.getallhotel();
	}

	
	@PostMapping("/newHotel")
	public Hotel addNewhotel(@RequestBody Hotel hotel) {
		System.out.println("in add new category " + hotel);
		return hotelservice.addNewHotel(hotel);
	}
	
	@DeleteMapping("/{hotelId}")
	public String deleteHotelDetails(@PathVariable Long hotelId)
	{
		System.out.println("in del category "+hotelId);
		return (hotelservice.deleteHotel(hotelId));
		
	}
	
	
	@PutMapping("/updatehotel")
	public Hotel updateHotelDetails(@RequestBody Hotel category)
	{
		System.out.println("in update "+category);
		return hotelservice.updateHotel(category);
	}
	
	@GetMapping("/alldestination")
	public List<DestinationDTO> listAllDestination() {
		System.out.println("in list all categories");
		return destinationService.getallDestination();
	}

}
