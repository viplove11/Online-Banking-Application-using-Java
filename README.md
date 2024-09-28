# Online Banking Application (Fortune First Bank)

## Project Overview
The **Online Banking Application** is a full-stack web application that enables users to securely manage their bank accounts and perform essential banking transactions online. This project offers core banking functionalities such as depositing and withdrawing money, generating mini-statements, checking balance, and transferring money between accounts.

## Key Features
1. **Deposit Money**  
   Users can securely deposit money into their bank accounts by entering the deposit amount and confirming the transaction.

2. **Withdraw Money**  
   Allows users to withdraw funds from their account, subject to balance availability.

3. **Mini-Statement Generation**  
   Displays the user's recent transactions including deposits, withdrawals, and transfers.

4. **Balance Enquiry**  
   Users can check their current account balance instantly.

5. **Account Transfer**  
   Provides the ability to transfer funds from one account to another securely.

## Tech Stack
The application has been developed using the following technologies:

- **Frontend**:  
  - HTML5: For structuring web pages.
  - CSS3: For styling the application.
  - JavaScript: For client-side validation and interactivity.

- **Backend**:  
  - Java: Core logic and server-side processing.
  - JSP (JavaServer Pages): For dynamically generating web content.
  - Servlets: For handling requests and responses.

- **Database**:  
  - MySQL: Used to store user account data, transaction history, and other related information.

## Setup and Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-repository/online-banking-application.git

## Setup and Installation

2. **Database Setup**
    
    - Install MySQL and create a database for the project.
    - Run the provided SQL scripts to set up the required tables (user accounts, transactions).
    
3. **Configure the Application**
    
    - Update the database connection details in the Java configuration files (such as `DBConfig.java` or `web.xml`).
    
4. **Deploy on Apache Tomcat**
    
    - Install and configure Apache Tomcat.
    - Deploy the project by placing the application’s WAR file in the `webapps` folder of Tomcat.
    
5. **Run the Application**
    
    - Open a browser and navigate to `http://localhost:8080/online-banking-application` to start using the application.
    
---

## How to Use

1. **Login/Register**: Users need to log in to their account using valid credentials or register for a new account.
    
2. **Navigate to Dashboard**: After logging in, users will be directed to the dashboard where they can access functionalities such as Deposit, Withdraw, Balance Enquiry, Mini Statement, and Account Transfer.
    
3. **Transaction History**: Users can view their recent transaction history via the mini-statement feature, displaying a summary of their financial activities.

---

## Future Enhancements

- Adding multi-factor authentication (MFA) for improved security.
- Implementing user notifications via email or SMS for each transaction.
- Developing a mobile-responsive version of the application for enhanced accessibility on smaller devices.
