# Notification Service

## Overview
Notification Service is a system that allows users to manage receivers and their contact information (email, phone, etc.) and send notifications via multiple channels like email, WhatsApp, and SMS. Users can create and manage message templates, making it easier to send consistent notifications. The service supports retry mechanisms for failed notifications and uses Kafka to handle communication between its components.

## Tech Stack
- **Java 21**
- **Spring Boot** (including Spring Security, Spring Data JPA)
- **Kafka** for message queuing and retry mechanism
- **Twilio** for SMS and WhatsApp integration
- **Spring Email Sender** for sending emails
- **PostgreSQL** for data storage

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/notification-service.git
   ```

2. **Set up PostgreSQL**:
   Create a new database in PostgreSQL:
   ```sql
   CREATE DATABASE notification_service;
   ```

3. **Configure the application**:
   Update the following credentials in the `application.yml` file:

   - PostgreSQL database credentials
   - Twilio credentials for SMS/WhatsApp
   - Email configuration for sending notifications

4. **Run the application**:
   Build and run the application as a standard Java app:
   ```bash
   ./mvnw spring-boot:run
   ```

## Configuration

Before running the application, make sure to update your `application.yml` file with the correct settings for:

- **Database**: Set your PostgreSQL credentials.
- **Twilio**: Add your Twilio account SID, authentication token, and phone numbers for SMS/WhatsApp.
- **Email**: Set the SMTP settings for your email provider (e.g., Gmail, Outlook).

## REST API Endpoints

**Authentication**
- `POST /api/auth/login` – Log in to the system.
- `POST /api/auth/register` – Register a new user.

**Message Templates**
- `POST /api/template` – Create a message template.
- `GET /api/template` – Get a list of message templates.
- `PATCH /api/template` – Update a message template.
- `DELETE /api/template/{id}` – Delete a message template by ID.

**Receivers**
- `POST /api/receiver` – Add a new receiver.
- `GET /api/receiver` – Get a paginated list of receivers.
- `POST /api/receiver/{id}/contact` – Add a contact to an existing receiver.

**Notifications**
- `POST /api/notification/send` – Send notifications through selected channels (email, WhatsApp, SMS).

## Features
- Send notifications via **email**, **WhatsApp**, and **SMS**.
- **Retry mechanism**: If sending a notification fails, the system will attempt 3 retries with a 5-second interval. If all attempts fail, the message is moved to a **dead-letter topic** for further inspection.
- **Message templates**: Users can create, update, and delete message templates for quick and consistent notification sending.
- **REST API** for managing users, receivers, and sending notifications.
  
## Architecture
The Notification Service consists of two separate services that communicate using Kafka:
1. **Notification Service**: Receives REST API requests for sending notifications. It sends the contact information and message content to Kafka.
2. **Sender Service**: Listens to Kafka topics, retrieves the messages, and sends notifications via the selected channels. If sending fails, it retries 3 times before moving the message to a dead-letter topic.