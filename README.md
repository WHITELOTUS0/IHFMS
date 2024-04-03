# Integrated Health Finance Management System (IHFMS)

## Description
The IHFMS is designed to streamline financial operations, enhance internal communication, and integrate health information systems with finance and accounting functionalities for MediCare Uganda Inc. The system now includes the ability to send emails and SMS messages using Sendinblue and Vonage, respectively, providing real-time communication between healthcare providers, administrative staff, and finance teams.

## Setup

### Prerequisites
- Java JDK 11 or higher
- Maven
- Access to Sendinblue and Vonage APIs

### Configuration
Create a `config.properties` file in the `src/main/resources` directory with the following content:

```properties
SENDINBLUE_API_KEY=your_sendinblue_api_key_here
VONAGE_API_KEY=your_vonage_api_key_here
VONAGE_API_SECRET=your_vonage_api_secret_here
```
Replace the placeholder values with your actual API keys. Do not commit this file to version control.

Building the Project
To build the project, navigate to the root directory where the `pom.xml` file is located and run:

```shell
mvn clean install
```
This command will compile the project and run any tests.

### Running the Application
After building the project, you can run the application using:

```shell
java -jar target/ihfms-1.0-SNAPSHOT.jar
```
Replace `ihfms-1.0-SNAPSHOT.jar` with the actual name of your compiled JAR file.

### Contributing
We welcome contributions to the IHFMS project. If you would like to contribute, please follow these steps:

- Fork the repository.
- Create a new branch for your feature or bug fix.
- Implement your changes.
- Write tests to cover the new functionality or fixes.
- Submit a pull request with a clear description of your changes.
- Please ensure your code adheres to the project's coding standards and that all tests pass before submitting a pull request.

### License
MIT License
