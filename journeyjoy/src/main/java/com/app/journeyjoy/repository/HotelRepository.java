package com.app.journeyjoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.journeyjoy.entities.Hotel;



public interface HotelRepository extends JpaRepository<Hotel,Long> {

}
