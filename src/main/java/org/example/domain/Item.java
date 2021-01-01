package org.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.example.domain.common.AbstractPersistable;
import org.example.domain.optional.ContainerStrengthComparator;
import org.example.domain.optional.ItemDifficultyComparator;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity(difficultyComparatorClass = ItemDifficultyComparator.class)
@NoArgsConstructor
@Data
public class Item extends AbstractPersistable {

    private double  width;
    private double length;
    private double height;
    private double weight;
    private boolean isReversible;

    private Container container;

    private Coordinate coordinates;

    public Item(long id, double width, double length, double height, double weight, boolean isReversible) {
        super(id);
        this.width  = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.isReversible = isReversible;
    }

    @PlanningVariable(valueRangeProviderRefs = {"containerRange"}, strengthComparatorClass = ContainerStrengthComparator.class)
    public Container getContainer() {
        return container;
    }

    @PlanningVariable(valueRangeProviderRefs = {"coordinateRange"})
    public Coordinate getCoordinates() {
        return coordinates;
    }

    public double getVolume(){
        return width * length * height;
    }

    @Override
    public String toString () {
        return String.format("Item %d: [W: %f ; L: %f ; H: %f] : %s", id, width, length, height, coordinates.toString());
    }

    public boolean widthOverlapsContainer(){
        return (this.coordinates.getX() >= this.container.getWidth()) ||
                (this.width + this.coordinates.getX() > this.container.getWidth());
    }

    public int widthOverlap() {
        return (int)((this.coordinates.getX() + this.width) - container.getWidth());
    }

    public boolean lengthOverlapsContainer() {
        return (this.coordinates.getY() >= this.container.getLength()) ||
                (this.length + this.coordinates.getY() > this.container.getLength());
    }

    public int lengthOverlap() {
        return (int)((this.coordinates.getY() + this.length) - container.getLength());
    }

    public boolean heightOverlapsContainer() {
        return (this.coordinates.getZ() >= this.container.getHeight()) ||
                (this.height + this.coordinates.getZ() > this.container.getHeight());
    }

    public int heightOverlap() {
        return (int)((this.coordinates.getZ() + this.height) - container.getHeight());
    }
    
    public boolean intersects(Item item) {
        return item.container.equals(this.container) &&
                (item.coordinates.getX() + item.getWidth() > this.coordinates.getX() &&
                        item.coordinates.getY() + item.getLength() > this.coordinates.getY() &&
                        item.coordinates.getZ() + item.getHeight() > this.coordinates.getZ() &&
                        item.coordinates.getX() < this.coordinates.getX() + this.width &&
                        item.coordinates.getY() < this.coordinates.getY() + this.length &&
                        item.coordinates.getZ() < this.coordinates.getZ() + this.height);
    }

    public int intersectArea(Item item) {
        if(intersects(item)) {
            return (int)(Math.abs(((item.getCoordinates().getX() + item.getWidth()) -
                    this.getCoordinates().getX()) *
                    ((item.getCoordinates().getY() + item.getLength()) -
                            this.getCoordinates().getY()) *
                    ((item.getCoordinates().getZ() + item.getHeight()) -
                            this.getCoordinates().getZ())));
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Item) {
            Item other = (Item) o;
            return new EqualsBuilder()
                    .append(id, other.id)
                    .append(container, other.container)
                    .append(coordinates, other.coordinates)
                    .isEquals();
        } else {
            return false;
        }
    }

}
