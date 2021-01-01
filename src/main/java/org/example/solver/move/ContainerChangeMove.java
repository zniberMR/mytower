package org.example.solver.move;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.example.domain.Container;
import org.example.domain.Item;
import org.example.domain.PackingSolution;
import org.optaplanner.core.api.score.director.ScoreDirector;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;

import java.util.Collection;
import java.util.Collections;

public class ContainerChangeMove extends AbstractMove<PackingSolution> {
    private Item item;
    private Container toContainer;

    public ContainerChangeMove(Item item, Container toContainer) {
        this.item = item;
        this.toContainer = toContainer;
    }

    @Override
    public Collection<?> getPlanningEntities() {
        return Collections.singletonList(item);
    }

    @Override
    public Collection<?> getPlanningValues() {
        return Collections.singletonList(toContainer);
    }

    @Override
    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !item.getContainer().equals(toContainer);
    }

    @Override
    public String toString() {
        return item + " -> " + toContainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ContainerChangeMove) {
            ContainerChangeMove other = (ContainerChangeMove) o;
            return new EqualsBuilder()
                    .append(item, other.item)
                    .append(toContainer, other.toContainer)
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(item)
                .append(toContainer)
                .toHashCode();
    }

    @Override
    protected AbstractMove<PackingSolution> createUndoMove(ScoreDirector<PackingSolution> scoreDirector) {
        return new ContainerChangeMove(item, item.getContainer());
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector<PackingSolution> scoreDirector) {
        scoreDirector.beforeVariableChanged(item, "container");
        item.setContainer(toContainer);
        scoreDirector.afterVariableChanged(item, "container");
    }
}
