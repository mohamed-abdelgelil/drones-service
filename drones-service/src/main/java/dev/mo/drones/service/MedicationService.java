package dev.mo.drones.service;

import java.util.List;

import dev.mo.drones.dto.MedicationDTO;

public interface MedicationService {

	void addMedicationToDrone(MedicationDTO medicationDTO, Long droneID);
	
	List<MedicationDTO> getMedications(Long droneID);
}
