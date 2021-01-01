package org.example.domain.common;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.api.domain.lookup.PlanningId;

@Setter
@NoArgsConstructor
public abstract class AbstractPersistable {

    protected long id;

    protected AbstractPersistable(long id) {
        this.id = id;
    }

    @PlanningId
    public long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .toHashCode();
    }

}
