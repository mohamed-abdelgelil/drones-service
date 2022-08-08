package dev.mo.drones.service;


import dev.mo.drones.dto.DroneDTO;
import dev.mo.drones.model.Drone;
import dev.mo.drones.model.DroneState;


public interface DroneService {

	Drone getDrone(Long droneID);
	
	DroneDTO addDrone(DroneDTO droneDTO) ;
	
	void updateDrone(Drone drone);

}
