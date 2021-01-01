package org.example.solver.move.factory;

import org.example.domain.Item;
import org.example.domain.PackingSolution;
import org.example.solver.move.ReverseItemMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;

import java.util.ArrayList;
import java.util.List;

public class ReverseItemMoveFactory implements MoveListFactory {

    @Override
    public List<? extends Move> createMoveList(Object solution) {
        PackingSolution packingSolution = (PackingSolution) solution;
        List<Move> moveList = new ArrayList<>();
        for(Item item : packingSolution.getItemList()) {
            moveList.add(new ReverseItemMove(item));
        }
        return moveList;
    }

}
