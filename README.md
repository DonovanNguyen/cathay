# ExchangeFlow

ExchangeFlow is an application for managing and synchronizing exchange rates.

## Project Structure

```
.
├── build.gradle
├── gradlew / gradlew.bat
├── settings.gradle
├── Dockerfile
├── src/
├── bin/
├── build/
├── openapi/
├── .vscode/
└── .idea/
```

## API Structure

- The root OpenAPI spec is `openapi.yaml`.
- Path definitions are found under `openapi/paths/`.
- Schema components are found under `openapi/components/schemas/`.

## Getting Started

1. **Build the project:**
   ```sh
   ./gradlew clean build
   ```

2. **Start the server:**
   ```sh
   ./gradlew bootRun
   ```

3. **Start the server using Docker:**
   - Build the Docker image:
     ```sh
     docker build -t exchangeflow .
     ```
   - Run the container:
     ```sh
     docker run -p 8080:8080 exchangeflow
     ```

## Project Information

1. **Architecture:**  
   This project follows Domain-Driven Design (DDD) principles for clear separation of concerns and maintainability.

2. **Main Features & APIs:**
   - `GET /api/currencies`  
     Retrieve a list of all currencies in the system.
   - `GET /api/currencies/{id}`  
     Retrieve details of a specific currency by its ID.
   - `POST /api/currencies`  
     Add a new currency. Requires currency code and name in the request body.
   - `PUT /api/currencies/{id}`  
     Update an existing currency by its ID. Requires updated currency data in the request body.
   - `DELETE /api/currencies/{id}`  
     Delete a currency from the system by its ID.
   - `GET /api/exchange-rates`  
     Fetch and transform exchange rate data from external APIs, then return the result.
   - **Batch Scheduler:**  
     Automatically syncs exchange rates at scheduled intervals.

3. **Logging:**  
   All API requests and responses, including calls to external APIs, are logged with their bodies for traceability.

4. **API Documentation:**  
   Swagger UI is available for easy exploration and testing of APIs.

5. **Docker Support:**  
   The application can be built and run easily using Docker.

6. **Error Handling:**  
   All API responses are standardized with consistent error handling.

7. **Possible Improvements:**  
   Consider using Retrofit or OkHttp instead of RestTemplate for calling external APIs.