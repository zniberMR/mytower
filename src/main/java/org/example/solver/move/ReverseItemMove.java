package org.example.solver.move;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.domain.Item;
import org.example.domain.PackingSolution;
import org.optaplanner.core.api.score.director.ScoreDirector;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;

import java.util.Collection;
import java.util.Collections;

public class ReverseItemMove extends AbstractMove<PackingSolution> {
    Item item;

    public ReverseItemMove(Item item) {
        this.item = item;
    }

    @Override
    public Collection<?> getPlanningEntities() {
        return Collections.singletonList(item);
    }

    @Override
    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return item.isReversible() && (item.getHeight() != item.getWidth() || item.getHeight() != item.getLength());
    }

    @Override
    public String toString() {
        return item + " Reversed";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ReverseItemMove) {
            ReverseItemMove other = (ReverseItemMove) o;
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
        return new ReverseItemMove(item);
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector<PackingSolution> scoreDirector) {
        scoreDirector.beforeProblemPropertyChanged(item);
        if (item.getHeight() != item.getWidth()){
            double temp = item.getHeight();
            item.setHeight(item.getWidth());
            item.setWidth(temp);
        } else {
            double temp = item.getHeight();
            item.setHeight(item.getLength());
            item.setLength(temp);
        }
        scoreDirector.afterProblemPropertyChanged(item);
    }
}
