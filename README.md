# Kafka Order Service

This project is a **Kafka-based order management system** implemented using **Java 17** and **Spring Boot 3**. It is designed to handle the creation, processing, and tracking of orders efficiently by leveraging the distributed messaging capabilities of Apache Kafka.

---

## Features

- **Order Management:** Create, update, and track orders.
- **Kafka Integration:** Publishes and consumes order-related messages via Kafka topics.
- **Scalability:** Built to handle a high volume of order events.
- **Spring Boot 3:** Provides a modern framework for building robust and efficient microservices.
- **Java 17:** Leverages the latest features of the Java language and runtime.

---

## Prerequisites

### Tools Required
- **Java 17** (Make sure it's installed and configured in your environment)
- **Apache Kafka** (Running Kafka broker and Zookeeper)
- **Maven 3.6+** (For dependency management and build)
- **Docker** (Optional: For running Kafka and Zookeeper using Docker containers)

---

## Setup and Installation

### Step 1: Clone the Repository
```bash
$ git clone https://github.com/cHRistianj98/online-store.git
$ cd online-store
```

### Step 2: Configure Kafka
Ensure that Kafka is running locally or on a specified server. Update the `application.yml` file with the correct Kafka broker URL and topic configurations.

### Step 3: Build and Run the Application
```bash
$ mvn clean install
$ java -jar target/online-store-0.0.1-SNAPSHOT.jar
```

---

## Kafka Topics

### Default Topics:
- **orders-created**: Publishes messages when new orders are created.
- **orders-updated**: Publishes messages when orders are updated.

Ensure these topics are created in your Kafka setup or configure the application to create them automatically.

---

## Running with Docker (Optional)

### Using Docker Compose
This project includes a `docker-compose.yml` file for setting up Kafka and Zookeeper easily.

```bash
$ docker-compose up -d
```

---

## API Endpoints

### Base URL
`http://localhost:8080/api/v1`

### Swagger UI endpoint
`http://localhost:8080/swagger-ui/index.html`

### Sample Endpoints
1. **Create Order**:
    - **POST** `/orders`
    - Request Body:
      ```json
      {
        "orderId": "1234",
        "product": "Laptop",
        "quantity": 2
      }
      ```

2. **Get Order by ID**:
    - **GET** `/orders/{id}`

3. **Update Order**:
    - **PUT** `/orders/{id}`
    - Request Body:
      ```json
      {
        "product": "Laptop",
        "quantity": 3
      }
      ```

---

## Contributing
Contributions are welcome! Please submit a pull request or open an issue to discuss your ideas.

---

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.