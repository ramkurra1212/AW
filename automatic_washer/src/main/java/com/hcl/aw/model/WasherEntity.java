package com.hcl.aw.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Ram kurra.
 */
@Entity
@Table(name = "Automatic_machine")
public class WasherEntity {

	@Id
	private int washing_id;
	private String model;
	private String serial;

	
	public int getWashing_id() {
		return washing_id;
	}

	public void setWashing_id(int washing_id) {
		this.washing_id = washing_id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	@Override
	public String toString() {
		return "WasherEntity [washing_id=" + washing_id + ", model=" + model + ", serial=" + serial + "]";
	}

	
}
