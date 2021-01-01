package org.example.solver.move;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.domain.Coordinate;
import org.example.domain.Item;
import org.example.domain.PackingSolution;
import org.optaplanner.core.api.score.director.ScoreDirector;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;

import java.util.Collection;
import java.util.Collections;

public class CoordinateChangeMove extends AbstractMove<PackingSolution> {
    private Item item;
    private Coordinate newCoordinate;

    public CoordinateChangeMove(Item item, Coordinate newCoordinate) {
        this.item = item;
        this.newCoordinate = newCoordinate;
    }

    @Override
    public Collection<?> getPlanningEntities() {
        return Collections.singletonList(item);
    }

    @Override
    public Collection<?> getPlanningValues() {
        return Collections.singletonList(newCoordinate);
    }

    @Override
    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !item.getCoordinates().equals(newCoordinate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof CoordinateChangeMove) {
            CoordinateChangeMove other = (CoordinateChangeMove) o;
            return new EqualsBuilder()
                    .append(item, other.item)
                    .append(newCoordinate, other.newCoordinate)
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(item)
                .append(newCoordinate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "" + item + " -> " + newCoordinate + " on " + item.getContainer();
    }

    @Override
    protected AbstractMove<PackingSolution> createUndoMove(ScoreDirector<PackingSolution> scoreDirector) {
        return new CoordinateChangeMove(item, item.getCoordinates());
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector<PackingSolution> scoreDirector) {
        scoreDirector.beforeVariableChanged(item, "coordinates");
        item.setCoordinates(newCoordinate);
        scoreDirector.afterVariableChanged(item, "coordinates");
    }
}
