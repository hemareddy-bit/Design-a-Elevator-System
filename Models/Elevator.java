package org.LLD.Models;

import org.LLD.Enums.Direction;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class representing an individual elevator.
 */
public class Elevator implements Runnable {
    private final int id;
    private final int capacity;
    private int currentFloor;
    private final Queue<Request> requests;
    private Direction currentDirection;

    public Elevator(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.currentFloor = 1;  // Default starting floor
        this.requests = new LinkedList<>();
        this.currentDirection = Direction.IDLE;
    }

    // Adds a request to the elevator if within capacity
    public synchronized void addRequest(Request request) {
        if (requests.size() < capacity) {
            requests.offer(request);
            System.out.println("Elevator " + id + " added request: " + request);
            notify();  // Notify thread to process the new request
        } else {
            System.out.println("Elevator " + id + " is at full capacity.");
        }
    }

    // Process the next request in the queue
    private synchronized void processRequest(Request request) {
        int sourceFloor = request.getSourceFloor();
        int destinationFloor = request.getDestinationFloor();

        // Move to the source floor first
        moveToFloor(sourceFloor);
        // Then move to the destination floor
        moveToFloor(destinationFloor);
    }

    // Simulates moving to a specific floor
    private void moveToFloor(int floor) {
        if (currentFloor < floor) {
            currentDirection = Direction.UP;
            while (currentFloor < floor) {
                currentFloor++;
                System.out.println("Elevator " + id + " reached floor " + currentFloor);
                simulateWait();
            }
        } else if (currentFloor > floor) {
            currentDirection = Direction.DOWN;
            while (currentFloor > floor) {
                currentFloor--;
                System.out.println("Elevator " + id + " reached floor " + currentFloor);
                simulateWait();
            }
        }
        currentDirection = Direction.IDLE;
    }

    // Simulates the wait time for moving between floors
    private void simulateWait() {
        try {
            Thread.sleep(1000); // Simulates the time it takes to move between floors
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Elevator processing requests in a separate thread
    @Override
    public void run() {
        while (true) {
            Request request;
            synchronized (this) {
                while (requests.isEmpty()) {
                    try {
                        wait();  // Wait until there is a request
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                request = requests.poll();
            }
            if (request != null) {
                processRequest(request);
            }
        }
    }

    public boolean isIdle() {
        return requests.isEmpty() && currentDirection == Direction.IDLE;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}
