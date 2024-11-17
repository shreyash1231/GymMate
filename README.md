
# Gym Management System Backend

This project is a backend application built using **Spring Boot** for gym owners (admins) to manage customer records efficiently. It includes features like customer record management, payment status tracking, and membership expiration tracking.

The backend uses **MySQL** for database storage and leverages **HTTP sessions** for user authentication and session management.

---

## Features

- **Admin Login**: Gym owners (admins) log in with their credentials.
- **OTP Verification**: An OTP is sent to the registered email for login verification.
- **Session Management**: After successful login and OTP verification, an admin session is created using `HttpSession`.
- **Customer Management**:
  - Add customer records.
  - View all customer records.
  - Track payment status (paid/remaining).
  - Check membership expiration.
- **Secure Authentication**: Ensures that only verified admins can access the system.

---

## Technology Stack

- **Backend Framework**: Spring Boot
- **Database**: MySQL
- **Session Management**: `HttpSession`
- **Authentication**: OTP verification via email
- **Programming Language**: Java

---

## Setup and Installation

### 1. Prerequisites

Ensure you have the following installed on your machine:
- Java Development Kit (JDK 17 or later)
- Maven
- MySQL
- Visual Studio Code (optional, but recommended)

### 2. Clone the Repository

```bash
git clone https://github.com/your-username/gym-management-system-backend.git
cd gym-management-system-backend
```

### 3. Configure the Database

- Create a MySQL database (e.g., `gym`).
- Update the `application.properties` file located in the `src/main/resources` directory with your database credentials:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/gym
  spring.datasource.username=your-username
  spring.datasource.password=your-password
  spring.jpa.hibernate.ddl-auto=update
  ```

### 4. Open the Project in VS Code

- Open Visual Studio Code and install the **Spring Boot Extension Pack** from the Extensions Marketplace.
- Import the project folder into VS Code.

### 5. Run the Application

#### Using VS Code:
1. Go to the **Run and Debug** section in VS Code.
2. Select the main class (usually `BackendApplication.java`) as the entry point.
3. Click **Run** or press `F5`.

#### Using Terminal:
Alternatively, you can run the application from the terminal:
```bash
mvn spring-boot:run
```

### 6. Access the Application

- Open your browser and navigate to:
  ```
  http://localhost:8080/
  ```
- To interact with the APIs, append the appropriate endpoint to the URL (e.g., `/admin`, `/allmembersdata`). For example:
  - Login endpoint: `http://localhost:8080/Authentication`
  - View all customers: `http://localhost:8080/allmembersdata`

---

## API Endpoints

### Authentication

- `POST /admin`: Admin login with username and password.
- `GET /otp/{otp}`: Verify OTP sent to the admin's email.

### Customer Management

- `POST /newmember`: Add a new customer record.
- `GET /allmembersdata`: Get all customer records.
- `PUT /updatedata/{id}`: Update customer details.
- `DELETE/removemember/{id}`: Delete a customer record.
- `GET /heckcheckexpirybyDate or GET/checkexpirybyAmount`: Check membership expiration and payment status.

## How to Use

1. **Start the Application**:
   - Ensure the database is running.
   - Run the Spring Boot application.

2. **Test the APIs**:
   - Use tools like **Postman** or **cURL** to send HTTP requests to the endpoints.
   - Example: `http://localhost:8080/Authentication` for admin login.

3. **Navigate Through Endpoints**:
   - Admins can manage customer data and track their status using the API.

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork this repository.
2. Create a new branch for your feature (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m "Add a new feature"`).
4. Push to your branch (`git push origin feature-name`).
5. Open a pull request.

---


## Contact

For questions or feedback, please feel free to contact me at [chandwadkarshreyash@gmail.com].
