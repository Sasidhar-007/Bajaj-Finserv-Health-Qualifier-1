package com.example.webhookapp;

import com.example.webhookapp.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebhookApplication implements CommandLineRunner {

    @Autowired
    private WebhookService webhookService;

    public static void main(String[] args) {
        SpringApplication.run(WebhookApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // This method runs after the application context is loaded
        System.out.println("Application started. Initiating webhook flow...");
        webhookService.executeWebhookFlow();
    }
}
