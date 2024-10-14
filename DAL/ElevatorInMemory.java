package org.LLD.DAL;

import org.LLD.Models.Elevator;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory implementation of ElevatorRepository.
 */
public class ElevatorInMemory implements ElevatorRepository {
    private final List<Elevator> elevators;

    public ElevatorInMemory(int numElevators, int capacity) {
        elevators = new ArrayList<>();
        for (int i = 1; i <= numElevators; i++) {
            elevators.add(new Elevator(i, capacity));
        }
    }

    @Override
    public List<Elevator> getAllElevators() {
        return elevators;
    }
}
