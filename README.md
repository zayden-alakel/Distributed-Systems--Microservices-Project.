# E-Commerce Microservices System

This is a microservices-based e-commerce system developed using Java Spring Boot. The system facilitates transactions between businesses and customers, businesses and businesses, as well as customers and customers.

## Project Structure

The project is composed of several microservices, each handling a distinct aspect of the e-commerce system:

- **Gateway Project**: Acts as the entry point for all requests, routing them to the appropriate microservices.
- **Eureka Server Project**: Manages service discovery, allowing microservices to find and communicate with each other.
- **Business Project**: Manages business-related operations.
- **Customer Project**: Handles customer-related activities.
- **Order Project**: Manages order processing and transactions.
- **Product Project**: Handles product catalog and inventory management.

## Features

- **Business Transactions**: Facilitates transactions between businesses and customers, businesses and businesses, and customers and customers.
- **Service Discovery**: Utilizes Eureka Server for microservice registration and discovery.
- **API Gateway**: Uses a gateway for routing and load balancing.
- **Modular Architecture**: Each microservice is independently deployable and scalable.


### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven

## Authors
- Rita Fattoum
- Zayden Alakel
