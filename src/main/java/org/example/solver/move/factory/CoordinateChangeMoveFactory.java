package org.example.solver.move.factory;

import org.example.domain.Coordinate;
import org.example.domain.Item;
import org.example.domain.PackingSolution;
import org.example.solver.move.CoordinateChangeMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;

import java.util.ArrayList;
import java.util.List;

public class CoordinateChangeMoveFactory implements MoveListFactory {

    @Override
    public List<? extends Move> createMoveList(Object solution) {
        List<Move> moveList = new ArrayList<>();
        PackingSolution packingSolution = (PackingSolution) solution;
        for(Item item : packingSolution.getItemList()) {
            for(Coordinate coordinate : packingSolution.getCoordinateList()) {
                moveList.add(new CoordinateChangeMove(item, coordinate));
            }
        }

        return moveList;
    }

}
