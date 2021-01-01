package org.example.controller;

import org.example.domain.PackingSolution;
import org.example.payload.PackingRequest;
import org.example.payload.PackingResponse;
import org.example.util.ModelMapper;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
//@RequestMapping("/myTower/containersFilling")
public class ContainersFillingController {
    @Autowired
    private SolverManager<PackingSolution, UUID> solverManager;

    @PostMapping("/solve")
    public PackingResponse solve(@RequestBody PackingRequest problem) {
        UUID problemId = UUID.randomUUID();

        SolverJob<PackingSolution, UUID> solverJob = solverManager.solve(problemId, ModelMapper.mapPackingRequestToPackingSolution(problem));

        PackingSolution solution;

        try {
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }

        return ModelMapper.mapPackingSolutionToPackingResponse(solution);
    }
}
