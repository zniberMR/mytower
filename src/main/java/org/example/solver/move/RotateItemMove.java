package org.example.solver.move;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.domain.Item;
import org.example.domain.PackingSolution;
import org.optaplanner.core.api.score.director.ScoreDirector;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;

import java.util.Collection;
import java.util.Collections;

public class RotateItemMove extends AbstractMove<PackingSolution> {
    Item item;

    public RotateItemMove(Item item) {
        this.item = item;
    }

    @Override
    public Collection<?> getPlanningEntities() {
        return Collections.singletonList(item);
    }

    @Override
    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return item.getWidth() != item.getLength();
    }

    @Override
    public String toString() {
        return item + " Rotated";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof RotateItemMove) {
            RotateItemMove other = (RotateItemMove) o;
            return new EqualsBuilder()
                    .append(item, other.item)
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(item)
                .toHashCode();
    }

    @Override
    protected AbstractMove<PackingSolution> createUndoMove(ScoreDirector<PackingSolution> scoreDirector) {
        return new RotateItemMove(item);
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector<PackingSolution> scoreDirector) {
        scoreDirector.beforeProblemPropertyChanged(item);
        double temp = item.getWidth();
        item.setWidth(item.getLength());
        item.setLength(temp);
        scoreDirector.afterProblemPropertyChanged(item);
    }
}
