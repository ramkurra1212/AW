package com.hcl.aw.process;

import javax.persistence.*;

import com.hcl.aw.model.JPA;



/**
 * Created by Ram kurra.
 */
@Entity
@Table(name = "AW_PROCESSES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Process extends JPA {
	private Long duration;
	private Integer temperature;
	private Integer rotationSpeed;

	public Process() {
	}

	public Process(Long duration, Integer temperature, Integer rotationSpeed) {
		this.duration = duration;
		this.temperature = temperature;
		this.rotationSpeed = rotationSpeed;
	}

	@Basic(optional = false)
	@Column(name = "duration")
	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	@Basic(optional = false)
	@Column(name = "temperature")
	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	@Basic(optional = false)
	@Column(name = "rpm")
	public Integer getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(Integer rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	@Override
	public String toString() {
		return "Process [duration=" + duration + ", temperature=" + temperature + ", rotationSpeed=" + rotationSpeed
				+ "]";
	}

}
