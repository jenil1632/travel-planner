package com.planmytrip.travelplanner.model;

import java.util.List;

public class GenerateContentResponse {

    private List<Candidate> candidates;

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    @Override
    public String toString() {
        return "GenerateContentResponse{" +
                "candidates=" + candidates +
                '}';
    }
}
