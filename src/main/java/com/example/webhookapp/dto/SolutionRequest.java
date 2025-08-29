package com.example.webhookapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolutionRequest {
    
    @JsonProperty("finalQuery")
    private String finalQuery;
    
    public SolutionRequest() {}
    
    public SolutionRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }
    
    // Getters and Setters
    public String getFinalQuery() {
        return finalQuery;
    }
    
    public void setFinalQuery(String finalQuery) {
        this.finalQuery = finalQuery;
    }
}