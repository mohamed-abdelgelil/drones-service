package dev.mo.drones.dto;

import java.io.Serializable;

public class MedicationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5265231349500447308L;

	private Long id;

	private String name;

	private int weight;

	private String code;

	private byte[] image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
