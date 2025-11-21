# Hall Booking Management System

## Overview
A **Java-based GUI application** for managing hall reservations at Hall Symphony Inc. The system allows scheduling, booking, and management of auditoriums, banquet halls, and meeting rooms. 
It demonstrates **object-oriented programming concepts** and uses **text files for data storage** (no external databases).

Four user roles are supported:

- **Scheduler:** Manages hall bookings and maintenance schedules.
- **Customer:** Registers, books halls, views bookings, and raises issues.
- **Administrator:** Manages staff and user accounts, oversees bookings.
- **Manager (My Role):** Oversees sales, resolves customer issues, and manages projects.

---

## My Contributions (Manager Role)
I implemented the **Manager features**, which included:

- **Sales Dashboard:** Viewing and filtering weekly, monthly, and yearly sales from bookings.
- **Customer Issue Management:** Viewing, assigning, and updating the status of customer-reported issues (e.g., In Progress, Done, Closed, Cancelled).
- **Project Management:** Creating new projects and tracking tasks related to hall operations.
- **Object-Oriented Techniques Applied:**
  - **Association/Aggregation:** Linking managers to sales records and customer issues.
  - **Abstraction:** Abstracted common user behaviors in base classes.
  - **Polymorphism:** Different behaviors implemented for different user types.
  - **Method Overloading & Overriding:** For managing sales records and issue updates.
- **Data Storage:** Used `.txt` files to store and retrieve all records securely.

---

## Technologies Used
- **Language:** Java  
- **GUI:** Java Swing / JFrames  
- **IDE:** NetBeans (or any Java IDE)  
- **Data Storage:** `.txt` files  
- **OOP Concepts:** Encapsulation, Inheritance, Polymorphism, Abstraction, Overloading, Overriding  

---

## Project Highlights
- Supports **booking, scheduling, and maintenance** for multiple halls.
- **Interactive GUI** with clear workflows for each user role.
- Demonstrates professional **OOP design** and modular coding practices.
- Fully functional **without external databases**, making it lightweight and portable.

---

## Installation & Usage
1. Clone or download the repository.  
2. Open the project in your Java IDE (NetBeans recommended).  
3. Compile and run the main class.  
4. Log in as different user roles to explore the features.

---

## Team Members & Roles
- Member 1: Scheduler Features  
- Member 2: Customer Features  
- Member 3: Administrator Features  
- Member 4 (Me): Manager Features (Sales, Customer Issues, Projects)

---

## Notes
- All data is stored in text files, ensure they are in the correct directory.  
- User input validation is implemented. 
