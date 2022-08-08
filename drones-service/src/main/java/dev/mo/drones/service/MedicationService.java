package dev.mo.drones.service;

import dev.mo.drones.dto.MedicationDTO;

public interface MedicationService {

	void addMedicationToDrone(MedicationDTO medicationDTO, Long droneID);
}
