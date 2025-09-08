# distributed-social-media-app

This project aims to build a social media platform to make people share their habits and make them stick to good habits.
Project is built using microservices architecture and dockerized.

## Table of Contents

- [Overview](#overview)
- [Architecture Components](#architecture-components)
- [Setup](#setup)
- [Consume API](#consuming-the-api)

## Overview

### Project Structure
``` 
distributed-social-media-app
├── api-gateway-v2
├── feed-service
├── notification-service
├── post-service-v2
├── users-service-v2
├── registry-service-v2
├── postman
├── docker-compose.yml
└── README.md # Project documentation
```


For now the project consists of 6 microservices:
- API Gateway: The main entry point through which all requests are routed.
- Registry Service: Enables microservices to discover each other.
- User Service: Manages user operations.
- Post Service: Manage post and comments.
- Notification Service: Manages notifications such as follow request approvals and updates related to user interactions.
- Feed Service: The Feed Service creates users' discover and friends feeds

## Architecture Components

### Services
- **API Gateway**: The API Gateway has been integrated into the system to provide clients with a single entry point to access multiple microservices through one URI. This simplifies the architecture by unifying access to all services, making it easier to manage and scale.
- **Registry Service**: A Registry Service has been created to help manage service discovery in the microservices architecture. It is a Spring-based microservice running on port 8761, acting as the main registry for all other services. This service keeps track of the different microservices and their host and port information, allowing them to register and be discovered dynamically.
- **User Service**: Handles user-related functionalities such as registration, login, profile management, and follow requests.
- **Post Service**: Manages posts and comments, allowing users to create, read, update, and delete posts and comments.
- **Notification Service**: Manages notifications related to user interactions, such as follow request approvals and updates.
- **Feed Service**: The Feed Service creates users' discover and friends feeds without using a database for now. It gets the needed user data from the User Service, such as profiles and connections. The discover feed shows content from users outside the connections, while the friends feed focuses on posts from existing connections. This simple design makes the service easy to use and flexible for future improvements.

### Databases
- **MySql**: Used for User Service, Post Service to store user and post data.
- **MongoDB**: Used for Notification Service to store notification data.

## Setup

Follow these steps to set up the project locally.

### Prerequisites

- Docker
- Docker Compose

### Steps

1. Clone this repository:
   ```sh
   git clone https://github.com/Tunao2den/distributed-social-media-app.git

2. Change to the project directory:
   ```sh
   cd distributed-social-media-app

3. Build the Docker images:
   ```sh
   docker-compose build

4. Start the Docker containers:
   ```sh
    docker-compose up

## Consuming the API

You can test the APIs using the provided **Postman collection**.

### Postman Collection Import
1. Open **Postman** on your machine
2. Click **Import** in the top left corner
3. Navigate to and select the collection file: `postman/distributed-social-media-app.postman_collection.json`
4. The collection will be imported with all available endpoints


### Example API Endpoint

**Endpoint:** `POST /users-service-v2/register`

**Base URL:** `http://localhost:8765`

#### Request Format

```json
{
    "firstName": "string",
    "lastName": "string", 
    "username": "string",
    "email": "string",
    "password": "string",
    "birthDate": "YYYY-MM-DD"
}
```

#### Example Request

```bash
curl -X POST http://localhost:8765/users-service-v2/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "tuna",
    "lastName": "ozden",
    "username": "tunaoz",
    "email": "tunaoz@gmail.com",
    "password": "secure123",
    "birthDate": "2000-01-01"
  }'
```

#### Success Response

**Status Code:** `200 OK`

```json
{
    "message": "User registered successfully!"
}
```