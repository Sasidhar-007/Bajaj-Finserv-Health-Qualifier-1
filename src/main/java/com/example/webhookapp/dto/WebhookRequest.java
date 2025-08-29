package com.example.webhookapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookRequest {
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("regNo")
    private String regNo;
    
    @JsonProperty("email")
    private String email;
    
    public WebhookRequest() {}
    
    public WebhookRequest(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getRegNo() {
        return regNo;
    }
    
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}