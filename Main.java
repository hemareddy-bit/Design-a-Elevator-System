package org.LLD;

import org.LLD.DAL.ElevatorInMemory;
import org.LLD.DAL.RequestInMemory;
import org.LLD.Models.Request;
import org.LLD.Services.ElevatorService;
import org.LLD.Services.RequestService;

public class Main {
    public static void main(String[] args) {
        // Create in-memory repositories
        ElevatorInMemory elevatorRepository = new ElevatorInMemory(3, 5);  // 3 elevators, capacity 5
        RequestInMemory requestRepository = new RequestInMemory();

        // Create services
        ElevatorService elevatorService = new ElevatorService(elevatorRepository);
        RequestService requestService = new RequestService(requestRepository);

        // Add requests and process them
        Request request1 = new Request(1, 5);
        requestService.addRequest(request1);
        elevatorService.addRequestToOptimalElevator(request1);

        Request request2 = new Request(3, 7);
        requestService.addRequest(request2);
        elevatorService.addRequestToOptimalElevator(request2);

        Request request3 = new Request(8, 2);
        requestService.addRequest(request3);
        elevatorService.addRequestToOptimalElevator(request3);

        // Start elevator threads
        elevatorRepository.getAllElevators().forEach(elevator -> {
            Thread elevatorThread = new Thread(elevator);
            elevatorThread.start();
        });
    }
}
