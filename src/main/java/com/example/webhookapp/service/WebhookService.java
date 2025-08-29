package com.example.webhookapp.service;

import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String TEST_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    public void executeWebhookFlow() {
        try {
            // Step 1: Generate webhook
            System.out.println("Step 1: Generating webhook...");
            WebhookResponse webhookResponse = generateWebhook();
            
            if (webhookResponse != null) {
                System.out.println("Webhook generated successfully!");
                System.out.println("Webhook URL: " + webhookResponse.getWebhook());
                System.out.println("Access Token: " + webhookResponse.getAccessToken());
                
                // Step 2: Solve SQL problem based on registration number
                String sqlQuery = solveSqlProblem("22BCE9583"); // You can change this registration number
                System.out.println("Generated SQL Query: " + sqlQuery);
                
                // Step 3: Submit solution
                System.out.println("Step 2: Submitting solution...");
                submitSolution(webhookResponse.getAccessToken(), sqlQuery);
                
            } else {
                System.out.println("Failed to generate webhook");
            }
            
        } catch (Exception e) {
            System.err.println("Error in webhook flow: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private WebhookResponse generateWebhook() {
        try {
            // Create request body
            WebhookRequest request = new WebhookRequest("John Doe", "REG12347", "john@example.com");
            
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            // Create HTTP entity
            HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);
            
            // Make POST request
            ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(
                GENERATE_WEBHOOK_URL, entity, WebhookResponse.class);
            
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                System.err.println("Failed to generate webhook. Status: " + response.getStatusCode());
                return null;
            }
            
        } catch (Exception e) {
            System.err.println("Error generating webhook: " + e.getMessage());
            return null;
        }
    }

    private String solveSqlProblem(String regNo) {
        // Extract last two digits from registration number
        String lastTwoDigits = regNo.substring(regNo.length() - 2);
        int lastTwoDigitsInt = Integer.parseInt(lastTwoDigits);
        
        System.out.println("Registration Number: " + regNo);
        System.out.println("Last two digits: " + lastTwoDigits);
        System.out.println("Question type: " + (lastTwoDigitsInt % 2 == 1 ? "Odd (Question 1)" : "Even (Question 2)"));
        
        // Based on the provided document, this is Question 1 (for odd registration numbers)
        // Find the highest salary not credited on 1st day of any month
        String sqlQuery = """
            SELECT 
                p.AMOUNT as SALARY,
                CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) as NAME,
                FLOOR(DATEDIFF(CURDATE(), e.DOB) / 365.25) as AGE,
                d.DEPARTMENT_NAME
            FROM PAYMENTS p
            INNER JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID
            INNER JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID
            WHERE DAY(p.PAYMENT_TIME) != 1
            ORDER BY p.AMOUNT DESC
            LIMIT 1""";
            
        return sqlQuery.trim();
    }

    private void submitSolution(String accessToken, String sqlQuery) {
        try {
            // Create request body
            SolutionRequest request = new SolutionRequest(sqlQuery);
            
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);
            
            // Create HTTP entity
            HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);
            
            // Make POST request
            ResponseEntity<String> response = restTemplate.postForEntity(
                TEST_WEBHOOK_URL, entity, String.class);
            
            System.out.println("Solution submission response status: " + response.getStatusCode());
            System.out.println("Solution submission response body: " + response.getBody());
            
        } catch (Exception e) {
            System.err.println("Error submitting solution: " + e.getMessage());
            e.printStackTrace();
        }
    }
}