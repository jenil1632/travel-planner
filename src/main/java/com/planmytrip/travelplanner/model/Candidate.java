package com.planmytrip.travelplanner.model;

public class Candidate {

    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "content=" + content +
                '}';
    }
}
