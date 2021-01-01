package org.example.domain;

import lombok.Setter;
import org.example.domain.common.AbstractPersistable;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.Iterator;
import java.util.List;

@PlanningSolution
@Setter
public class PackingSolution {

    private List<Item> itemList;
    private List<Container> containerList;
    private List<Coordinate> coordinateList;

    private HardSoftScore score;

    @PlanningEntityCollectionProperty
    public List<Item> getItemList() {
        return itemList;
    }

    @ValueRangeProvider(id = "containerRange")
    @ProblemFactCollectionProperty
    public List<Container> getContainerList() {
        return containerList;
    }

    @ValueRangeProvider(id = "coordinateRange")
    @ProblemFactCollectionProperty
    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PackingSolution)) {
            return false;

        } else {
            PackingSolution other = (PackingSolution) o;
            if(itemList.size() != other.itemList.size()) {
                return false;
            }
            for(Iterator<Item> it = itemList.iterator(), otherIt = other.itemList.iterator(); it.hasNext();) {
                Item item = it.next();
                Item otherItem = otherIt.next();
                if(!item.equals(otherItem)) {
                    return false;
                }
            }
            return true;
        }
    }

}
