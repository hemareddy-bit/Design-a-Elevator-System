package org.LLD.DAL;

import org.LLD.Models.Request;
import java.util.Queue;

/**
 * Interface defining methods for request repository.
 */
public interface RequestRepository {
    Queue<Request> getAllRequests();
}
