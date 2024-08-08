package com.app.journeyjoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.journeyjoy.entities.Destination;

public interface DestinationRepository extends JpaRepository<Destination,Long> {

}
