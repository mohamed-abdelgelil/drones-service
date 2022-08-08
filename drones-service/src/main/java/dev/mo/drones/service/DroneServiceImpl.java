package dev.mo.drones.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mo.drones.dto.DroneDTO;
import dev.mo.drones.model.Drone;
import dev.mo.drones.model.DroneState;
import dev.mo.drones.repository.DroneRepository;

@Service
public class DroneServiceImpl implements DroneService {

	@Autowired
	DroneRepository droneRepository;

	@Override
	public Drone getDrone(Long droneID) {
		return droneRepository.findDroneById(droneID);
	}

	@Transactional
	@Override
	public DroneDTO addDrone(DroneDTO droneDTO) {
		if (droneDTO.getSerial().length() > 100) {
			// throw Exception
		}

		Drone drone = new Drone();
		drone.setSerial(droneDTO.getSerial());
		drone.setModel(droneDTO.getModel());
		droneRepository.save(drone);
		droneDTO.setId(drone.getId());
		return droneDTO;
	}

	@Transactional
	@Override
	public void updateDrone(Drone drone) {
		droneRepository.save(drone);
	}

}
