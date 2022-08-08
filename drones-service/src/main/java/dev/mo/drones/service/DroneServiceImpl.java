package dev.mo.drones.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drones.config.DroneException;

import dev.mo.drones.dto.DroneDTO;
import dev.mo.drones.model.Drone;
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

	@Override
	public List<DroneDTO> getAvailableDrones() {
		List<DroneDTO> droneDTOs = new ArrayList<>();
		List<Drone> drones = droneRepository.findAvailableDrones();
		for (Drone drone : drones) {
			DroneDTO droneDTO = new DroneDTO();
			droneDTO.setBtCapacity(drone.getBtCapacity());
			droneDTO.setModel(drone.getModel());
			droneDTO.setSerial(drone.getSerial());
			droneDTO.setState(String.valueOf(drone.getState()));
			droneDTO.setWeight(drone.getWeight());

			droneDTOs.add(droneDTO);
		}
		return droneDTOs;

	}
	
	@Override
	public int getBattaryLevel(Long droneID) {
		Drone drone = dronRepository.findDroneById(droneID);
		if (drone == null) {
			throw new DroneException("DR003", "Drone Not Found");
		}
		if (drone.getBtCapacity() < 25) {
			LOG.info("**********  Drone " + drone.getSerial() + " Battery is very Low  **************");
		}
		return drone.getBtCapacity();
	}

}
