package org.LLD.DAL;

import org.LLD.Models.Request;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In-memory implementation of RequestRepository.
 */
public class RequestInMemory implements RequestRepository {
    private final Queue<Request> requests;

    public RequestInMemory() {
        this.requests = new LinkedList<>();
    }

    @Override
    public Queue<Request> getAllRequests() {
        return requests;
    }

    public void addRequest(Request request) {
        requests.offer(request);
    }
}
