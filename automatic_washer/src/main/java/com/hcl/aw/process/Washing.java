package com.hcl.aw.process;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Ram kurra.
 */
@Entity
@DiscriminatorValue("washing")
public class Washing extends Process {
    public Washing() {
    }

    public Washing(Long duration, Integer temperature, Integer rotationSpeed) {
        super(duration, temperature, rotationSpeed);
    }

    @Override
    public String toString() {
        return "Washing " + super.toString();
    }
}