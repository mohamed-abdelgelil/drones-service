package dev.mo.drones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import dev.mo.drones.model.Drone;


@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
	
	@Query("SELECT d FROM Drone d WHERE d.id = ?1")
	Drone findDroneById(Long droneID);
	
	@Query("SELECT d FROM Drone d WHERE d.state IN ('IDLE','RETURNING')")
	List<Drone> findAvailableDrones();
	
	@Query("SELECT d FROM Drone d WHERE d.btCapacity < 25")
	List<Drone> findLowBattartLevels();

}
