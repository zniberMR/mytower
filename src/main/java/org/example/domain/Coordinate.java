package org.example.domain;

import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.math.BigDecimal;

@Setter
public class Coordinate {
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal z;

    public Coordinate(BigDecimal x, BigDecimal y, BigDecimal z) {
        setCoordinates(x, y, z);
    }

    public Coordinate() {
        this(new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"));
    }

    public double getZ() {
        return z.doubleValue();
    }

    public double getY() {
        return y.doubleValue();
    }

    public double getX() {
        return x.doubleValue();
    }

    public void setCoordinates(BigDecimal x, BigDecimal y, BigDecimal z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Coordinate) {
            Coordinate other = (Coordinate) o;
            return new EqualsBuilder()
                    .append(x, other.x)
                    .append(y, other.y)
                    .append(z, other.z)
                    .isEquals();
        } else {
            return false;
        }
    }
}
