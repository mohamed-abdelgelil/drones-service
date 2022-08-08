package dev.mo.drones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.mo.drones.dto.DroneDTO;
import dev.mo.drones.service.DroneService;

@RestController
@RequestMapping("/drone")
public class DroneController {

	@Autowired
	DroneService droneService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public DroneDTO addDrone(@RequestBody DroneDTO droneDTO) {
		return droneService.addDrone(droneDTO);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<DroneDTO> getAvailableDrone() {
		return droneService.getAvailableDrones();
	}

	@RequestMapping(value = "/{droneID}/battary/get", method = RequestMethod.GET, headers = "Accept=application/json")
	public int getDroneBattary(@PathVariable Long droneID) {
		return droneService.getBattaryLevel(droneID);
	}

}
