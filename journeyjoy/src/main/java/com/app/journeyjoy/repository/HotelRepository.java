package com.app.journeyjoy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.journeyjoy.entities.Hotel;



public interface HotelRepository extends JpaRepository<Hotel,Long> {
	List<Hotel> findByDestinations(Long destinationid); 
}
