package org.LLD.Services;

import org.LLD.DAL.ElevatorRepository;
import org.LLD.Models.Elevator;
import org.LLD.Models.Request;

import java.util.List;

/**
 * Service for handling elevator operations.
 */
public class ElevatorService {
    private final ElevatorRepository elevatorRepository;

    public ElevatorService(ElevatorRepository elevatorRepository) {
        this.elevatorRepository = elevatorRepository;
    }

    public void addRequestToOptimalElevator(Request request) {
        List<Elevator> elevators = elevatorRepository.getAllElevators();
        Elevator optimalElevator = findOptimalElevator(elevators, request);
        optimalElevator.addRequest(request);
    }

    private Elevator findOptimalElevator(List<Elevator> elevators, Request request) {
        Elevator optimalElevator = null;
        int minDistance = Integer.MAX_VALUE;
        int sourceFloor = request.getSourceFloor();

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - sourceFloor);
            if (distance < minDistance && elevator.isIdle()) {
                minDistance = distance;
                optimalElevator = elevator;
            }
        }
        return optimalElevator != null ? optimalElevator : elevators.get(0);  // Fallback to first elevator
    }
}
