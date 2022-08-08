package dev.mo.drones.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import dev.mo.drones.dto.DroneDTO;
import dev.mo.drones.exception.DroneException;
import dev.mo.drones.model.Drone;
import dev.mo.drones.repository.DroneRepository;

@Service
public class DroneServiceImpl implements DroneService {

	private static final Logger LOG = LoggerFactory.getLogger(DroneServiceImpl.class);

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
			throw new DroneException("DR001", "Serial Exceed Limit of 100");
		}

		Drone drone = new Drone();
		drone.setSerial(droneDTO.getSerial());
		drone.setModel(droneDTO.getModel());
		droneRepository.save(drone);

		droneDTO.setId(drone.getId());
		droneDTO.setBtCapacity(drone.getBtCapacity());
		droneDTO.setModel(drone.getModel());
		droneDTO.setSerial(drone.getSerial());
		droneDTO.setState(String.valueOf(drone.getState()));
		droneDTO.setWeight(drone.getWeight());
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
			droneDTO.setId(drone.getId());
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
		Drone drone = this.getDrone(droneID);
		if (drone == null) {
			throw new DroneException("DR003", "Drone Not Found");
		}
		if (drone.getBtCapacity() < 25) {
			LOG.info("**********  Drone " + drone.getSerial() + " Battery is very Low  **************");
		}
		return drone.getBtCapacity();
	}

	@Scheduled(fixedRate = 30000)
	private void batteryStateChecker() {
		List<Drone> drones = droneRepository.findLowBattartLevels();
		for (Drone drone : drones) {
			LOG.warn("**********  Drone " + drone.getSerial() + " Battery is very Low  **************");
		}
	}

}
