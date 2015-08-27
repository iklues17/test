About Customers and Orders
===
This project is a simple example of an event-driven domain model.

Logically, there are two services:

* customer management - manages customers
* order management - manages orders

For simplicity they are, however, deployed as a single Spring Boot application.

Running MongoDB
==

The application uses MongoDB and RabbitMQ.
The easier way to run those locally is to use Docker Compose.

In the top-level directory, run this command:
```
docker-compose up -d mongodb rabbitmq
```

You also need to set some environment variables:
```
. ./set-env.sh
```

Building the application
==

You can build the application using these commands:
```
cd code/customers-and-orders
./gradlew build
```

This will build and test the application

Running the application
===
You can run the application using this command.
```
java -jar build/libs/customers-and-orders.jar
```

Accessing the REST API
===

Both services expose a REST API.

Let's suppose the application is running on localhost:8080.
You can now visit these URLs:

* http://localhost:8080/swagger-ui.html - browse the REST API and invoke operations
* http://localhost:8080/v2/api-docs- JSON specification of the API
