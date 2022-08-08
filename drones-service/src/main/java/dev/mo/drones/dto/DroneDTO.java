package dev.mo.drones.dto;

import java.io.Serializable;

public class DroneDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7789945395635464977L;

	private String serial;

	private String model;

	private int weight;

	private int btCapacity;

	private String state;

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getWeight() {
		return weight;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
