package com.hcl.aw.process;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Ram kurra.
 */
@Entity
@DiscriminatorValue("squeaking")
public class Squeaking extends Process {
    public Squeaking() {
    }

    public Squeaking(Long duration, Integer temperature, Integer rotationSpeed) {
        super(duration, temperature, rotationSpeed);
    }

    @Override
    public String toString() {
        return "Squeaking " + super.toString();
    }
}