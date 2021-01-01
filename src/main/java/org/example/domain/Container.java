package org.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.example.domain.common.AbstractPersistable;

@NoArgsConstructor
@Data
public class Container extends AbstractPersistable {

    private double        width;
    private double       length;
    private double       height;
    private double    weightMax;
    private int            cost;

    public Container(long id, double width, double length, double height, double weightMax, int cost) {
        super(id);
        this.width  = width;
        this.length = length;
        this.height = height;
        this.weightMax = weightMax;
        this.cost   = cost;
    }

    public double getVolume(){
        return width * length * height;
    }

    @Override
    public String toString() {
        return String.format("Container %d (%f,%f,%f)", id, width, length, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Container) {
            Container other = (Container) o;
            return new EqualsBuilder()
                    .append(id, other.id)
                    .isEquals();
        } else {
            return false;
        }
    }
}
