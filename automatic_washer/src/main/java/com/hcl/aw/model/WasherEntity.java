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
	private int washingId;
	private String model;
	private String serial;

	
	public int getwashingId() {
		return washingId;
	}

	public void setwashingId(int washing_id) {
		this.washingId = washing_id;
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
		return "WasherEntity [washingId=" + washingId + ", model=" + model + ", serial=" + serial + "]";
	}

	
}
