package org.LLD.Services;

import org.LLD.DAL.RequestRepository;
import org.LLD.Models.Request;

/**
 * Service for handling elevator requests.
 */
public class RequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void addRequest(Request request) {
        requestRepository.getAllRequests().offer(request);
        System.out.println("Request added: " + request);
    }
}
