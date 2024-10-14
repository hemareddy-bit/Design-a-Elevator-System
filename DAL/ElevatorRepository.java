package org.LLD.DAL;

import org.LLD.Models.Elevator;
import java.util.List;

/**
 * Interface defining methods for elevator repository.
 */
public interface ElevatorRepository {
    List<Elevator> getAllElevators();
}
