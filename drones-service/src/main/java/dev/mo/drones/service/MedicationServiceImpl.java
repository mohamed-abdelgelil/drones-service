package dev.mo.drones.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mo.drones.dto.MedicationDTO;
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
	public void addMedicationToDrone(MedicationDTO medicationDTO, Long droneID) {
		// getDroneByID
		Drone drone = droneService.getDrone(droneID);
		if (drone == null) {
			// throw exception
		}
		if (drone.getWeight() + medicationDTO.getWeight() > 500) {
			// throw exception
		}
		if (drone.getState() != DroneState.IDLE) {
			// throw exception
		}
		if (drone.getBtCapacity() < 25) {
			// throw exception
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

	}
	
	
	@Override
	public List<MedicationDTO> getMedications(Long droneID) {
		List<MedicationDTO> medicationDTOs = new ArrayList<MedicationDTO>();
		List<Medication> medications = medicationRepository.findMedicationsByDroneID(droneID);
		for (Medication medication : medications) {

			MedicationDTO medicationDTO = new MedicationDTO();
			medicationDTO.setCode(medication.getCode());
			medicationDTO.setImage(medication.getImage());
			medicationDTO.setName(medication.getName());
			medicationDTO.setWeight(medication.getWeight());

			medicationDTOs.add(medicationDTO);
		}
		return medicationDTOs;
	}
}
