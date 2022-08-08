package com.musala.drones;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import dev.mo.drones.DronesServiceApplication;
import dev.mo.drones.dto.DroneDTO;
import dev.mo.drones.dto.MedicationDTO;
import dev.mo.drones.service.DroneService;
import dev.mo.drones.service.MedicationService;

@SpringBootTest(classes = {DronesServiceApplication.class})
class DronesServiceApplicationTests {

	@Autowired 
	DroneService droneService;
	
	@Autowired
	MedicationService medicationService;
	
	@Test
	void addDrone() {
		DroneDTO droneDTO =new DroneDTO();
		droneDTO.setSerial("123456789");
		droneDTO.setModel("Lightweight");
		droneDTO = droneService.addDrone(droneDTO);
	}
	
	@Test
	void LoadMedication() {
		MedicationDTO medicationDTO1 = new MedicationDTO();
		medicationDTO1.setCode("ss150");
		medicationDTO1.setName("Puls");
		medicationDTO1.setWeight(100);
		medicationService.addMedicationToDrone(medicationDTO1, 1l);
	}

}
