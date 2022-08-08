package dev.mo.drones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Drone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String serial;

	private String model;

	private int weight = 0;

	private int btCapacity = 100; //battary full charged by defult

	@Column(name = "STATE")
	@Enumerated(EnumType.STRING)
	private DroneState state = DroneState.IDLE; // drone in idel state once created

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public int getWeight() {
		return weight;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getBtCapacity() {
		return btCapacity;
	}

	public void setBtCapacity(int btCapacity) {
		this.btCapacity = btCapacity;
	}

	public DroneState getState() {
		return state;
	}

	public void setState(DroneState state) {
		this.state = state;
	}

}
