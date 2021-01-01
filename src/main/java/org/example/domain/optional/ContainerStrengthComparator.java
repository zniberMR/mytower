package org.example.domain.optional;

import org.example.domain.Container;

import java.util.Comparator;

public class ContainerStrengthComparator implements Comparator<Container> {

    private static final Comparator<Container> COMPARATOR = Comparator.comparingDouble(Container::getVolume)
            .thenComparingLong(Container::getId);

    @Override
    public int compare(Container a, Container b) {
        return COMPARATOR.compare(a, b);
    }
}
