package org.example.domain.optional;

import org.example.domain.Item;

import java.util.Comparator;

public class ItemDifficultyComparator implements Comparator<Item> {

    private static final Comparator<Item> COMPARATOR = Comparator.comparingDouble(Item::getVolume)
            .thenComparingLong(Item::getId);

    @Override
    public int compare(Item a, Item b) {
        return COMPARATOR.compare(a, b);
    }

}
