package dev.mo.drones.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


import dev.mo.drones.dto.DroneDTO;
import dev.mo.drones.model.Drone;
import dev.mo.drones.repository.DroneRepository;

public class DroneServiceImpl implements DroneService {

	@Autowired
	DroneRepository droneRepository;

	@Transactional
	@Override
	public DroneDTO addDrone(DroneDTO droneDTO) {
		if (droneDTO.getSerial().length() > 100) {
			//throw Exception
		}
		
		Drone drone = new Drone();
		drone.setSerial(droneDTO.getSerial());
		drone.setModel(droneDTO.getModel());
		droneRepository.save(drone);
		droneDTO.setId(drone.getId());
		return droneDTO;
	}
}
