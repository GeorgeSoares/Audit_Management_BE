# Audit Management Back End

This repository contains the back-end of the Audit Management application for my Assignment for the module "Project Java and Web Development (DLBCSPJWD01)" at the IU.

This application is supposed to be used in integration with the Front-end application.

Link to the repository of the front-end: [https://github.com/GeorgeSoares/Audit_Management_FE](https://github.com/GeorgeSoares/Audit_Management_FE)

### Pre-requisites

1. Java Development Kit (JDK) 17 or higher
2. Apache Maven
3. Docker

## How to Run the Back-end Application with Docker

To get the back-end application running locally using Docker, follow these steps:

1.  **Clone the Repository:**
    First, clone this repository to your local machine:

    ```bash
    git clone https://github.com/GeorgeSoares/Audit_Management_BE.git
    ```

2.  **Compile the Project:** 
    Use the Maven Wrapper to ensure the correct Maven version is used. This command will build a new .jar package in the target/ folder.

    ```bash
    ./mvnw clean package
    ```
    

3. **Configure Environment Variable:** 
   Create a new file named .env in the root of the Audit_Management_BE directory (at the same level as docker-compose.yml) with the following content:

    ```bash
    DB_URL=jdbc:postgresql://postgres:5432/audit_mgmt
    DB_USERNAME=postgres
    DB_PASSWORD=password
    ```

4. **Build and run with Docker Compose:**
    With the compiled .jar file, you can now build the Docker image and start the container using Docker Compose in detached mode.

    ```bash
    docker compose up --build -d
    ```

5. **Access the application through the Front-end:** 
    The application will be able to access the database and perform all necessary CRUD operations.

    Link for access the application (Front end container muss be running!): 
 
    [http://localhost:8081](http://localhost:8081)

---