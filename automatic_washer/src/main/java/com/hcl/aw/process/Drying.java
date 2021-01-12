package com.hcl.aw.process;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Ram kurra.
 */
@Entity
@DiscriminatorValue("drying")
public class Drying extends Process {
    public Drying() {
    }

    public Drying(Long duration, Integer temperature, Integer rotationSpeed) {
        super(duration, temperature, rotationSpeed);
    }

    @Override
    public String toString() {
        return "Drying " + super.toString();
    }
}