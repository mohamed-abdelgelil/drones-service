package dev.mo.drones.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mo.drones.dto.MedicationDTO;
import dev.mo.drones.exception.DroneException;
import dev.mo.drones.model.Drone;
import dev.mo.drones.model.DroneState;
import dev.mo.drones.model.Medication;
import dev.mo.drones.repository.MedicationRepository;

@Service
public class MedicationServiceImpl implements MedicationService {

	@Autowired
	DroneService droneService;

	@Autowired
	MedicationRepository medicationRepository;

	@Transactional
	@Override
	public MedicationDTO addMedicationToDrone(MedicationDTO medicationDTO, Long droneID) {
		// getDroneByID
		Drone drone = droneService.getDrone(droneID);
		if (drone == null) {
			throw new DroneException("DR003", "Drone Not Found");
		}
		if (drone.getWeight() + medicationDTO.getWeight() > 500) {
			throw new DroneException("DR004", "Weght Exceed the Limit of 500");
		}
		if (drone.getState() != DroneState.IDLE) {
			throw new DroneException("DR005", "Drone is Not Available");
		}
		if (drone.getBtCapacity() < 25) {
			throw new DroneException("DR006", "Drone Battery very Low");
		}

		// Loading
		drone.setState(DroneState.LOADING);
		droneService.updateDrone(drone);

		Medication medication = new Medication();
		medication.setCode(medicationDTO.getCode());
		medication.setImage(medicationDTO.getImage());
		medication.setName(medicationDTO.getName());
		medication.setWeight(medicationDTO.getWeight());
		medication.setDrone(drone);
		medicationRepository.save(medication);

		drone.setState(DroneState.LOADED);
		drone.setWeight(drone.getWeight() + medicationDTO.getWeight());
		droneService.updateDrone(drone);
		// Loaded
		
		
		// Prepare Response
		medicationDTO.setId(medication.getId());
		medicationDTO.setCode(medication.getCode());
		medicationDTO.setImage(medication.getImage());
		medicationDTO.setName(medication.getName());
		medicationDTO.setWeight(medication.getWeight());
		return medicationDTO;

	}
	
	
	@Override
	public List<MedicationDTO> getMedications(Long droneID) {
		List<MedicationDTO> medicationDTOs = new ArrayList<MedicationDTO>();
		List<Medication> medications = medicationRepository.findMedicationsByDroneID(droneID);
		for (Medication medication : medications) {

			MedicationDTO medicationDTO = new MedicationDTO();
			medicationDTO.setId(medication.getId());
			medicationDTO.setCode(medication.getCode());
			medicationDTO.setImage(medication.getImage());
			medicationDTO.setName(medication.getName());
			medicationDTO.setWeight(medication.getWeight());

			medicationDTOs.add(medicationDTO);
		}
		return medicationDTOs;
	}
}
