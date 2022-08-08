package dev.mo.drones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.mo.drones.model.Medication;


@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

	@Query("SELECT M FROM Medication M WHERE M.drone.id = ?1")
	List<Medication> findMedicationsByDroneID(Long droneID);

}
