 # Bajaj Finserv Health - Webhook Application

A Spring Boot application that automatically generates webhooks, solves SQL problems, and submits solutions via REST API.

## 📋 Project Overview

This application implements the Bajaj Finserv Health qualifier requirements:
- Sends POST request to generate webhook on startup
- Solves SQL problems based on registration number (odd/even logic)
- Submits SQL solution using JWT authentication
- No manual endpoints required - fully automated flow

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3.2.0**
- **Maven**
- **RestTemplate** for HTTP requests
- **Jackson** for JSON processing

## 📁 Project Structure

```
webhook-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/webhookapp/
│   │   │       ├── WebhookApplication.java          # Main application
│   │   │       ├── config/
│   │   │       │   └── AppConfig.java               # Configuration
│   │   │       ├── dto/
│   │   │       │   ├── WebhookRequest.java          # Request DTOs
│   │   │       │   ├── WebhookResponse.java         # Response DTOs
│   │   │       │   └── SolutionRequest.java         # Solution DTO
│   │   │       └── service/
│   │   │           └── WebhookService.java          # Main business logic
│   │   └── resources/
│   │       └── application.properties               # Spring Boot config
├── target/
│   └── webhook-app.jar                             # Executable JAR
├── pom.xml                                         # Maven configuration
├── README.md                                       # This file
└── .gitignore                                      # Git ignore rules
```

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Internet connection (for API calls)

### Build and Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/YOUR_USERNAME/webhook-app.git
   cd webhook-app
   ```

2. **Build the project:**
   ```bash
   mvn clean package
   ```

3. **Run the application:**
   ```bash
   java -jar target/webhook-app.jar
   ```
   
   Or using Maven:
   ```bash
   mvn spring-boot:run
   ```

## 🔧 Configuration

### Personal Details
Update your personal information in `WebhookService.java`:

```java
// Line 45: Update with your details
WebhookRequest request = new WebhookRequest("Your Name", "YOUR_REG_NO", "your.email@example.com");

// Line 32: Update registration number
String sqlQuery = solveSqlProblem("YOUR_REG_NUMBER");
```

### API Endpoints
The application uses these endpoints:
- **Generate Webhook:** `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
- **Submit Solution:** `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`

## 📊 SQL Problem Logic

The application automatically determines which SQL question to solve:

- **Odd registration numbers (last 2 digits)** → Question 1
- **Even registration numbers (last 2 digits)** → Question 2

### Current Implementation (Question 1)
Finds the highest salary not credited on the 1st day of any month:

```sql
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
LIMIT 1
```

## 📝 Expected Output

When the application runs successfully, you'll see:

```
Application started. Initiating webhook flow...
Step 1: Generating webhook...
Webhook generated successfully!
Webhook URL: [generated-webhook-url]
Access Token: [jwt-token]
Registration Number: REG12347
Last two digits: 47
Question type: Odd (Question 1)
Generated SQL Query: [sql-query]
Step 2: Submitting solution...
Solution submission response status: 200 OK
Solution submission response body: [api-response]
```

## 🛡️ Error Handling

The application includes comprehensive error handling for:
- Network connectivity issues
- API request failures
- JSON parsing errors
- Authentication problems

## 📦 JAR File

The executable JAR file is located at: `target/webhook-app.jar`

**Download Link:** [Raw JAR Download](https://github.com/YOUR_USERNAME/webhook-app/raw/main/target/webhook-app.jar)

## 🔗 Submission Links

- **GitHub Repository:** https://github.com/YOUR_USERNAME/webhook-app.git
- **JAR Download:** https://github.com/YOUR_USERNAME/webhook-app/raw/main/target/webhook-app.jar

## 👨‍💻 Author

**Your Name**
- Registration: 22bce9583
- Email: sasidhar.22bce9583@vitapstudent.ac.in

## 📄 License

This project is created for Bajaj Finserv Health technical assessment.

---

**Note:** Make sure to update the personal details and GitHub links before submission!
