package dev.mo.drones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.mo.drones.dto.MedicationDTO;
import dev.mo.drones.service.MedicationService;

@RestController
@RequestMapping("/medication")
public class MedicationController {

	@Autowired
	MedicationService medicationService;

	@RequestMapping(value = "/add/to/{droneID}", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addMedicationToDrone(@PathVariable Long droneID, @RequestBody MedicationDTO medicationDTO) {
		medicationService.addMedicationToDrone(medicationDTO, droneID);
	}

	@RequestMapping(value = "/get/from/{droneID}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<MedicationDTO> getDroneMedication(@PathVariable Long droneID) {
		return medicationService.getMedications(droneID);
	}

}
