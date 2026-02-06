# Bus-Travelling-Agency
Bus Travelling Agency – Java JDBC Project A fun, console-based bus booking system built for learning Java and JDBC with MySQL.

# Bus Travelling Agency – Java JDBC Project

A console-based bus booking system built for **learning and practice** using **Java and JDBC** with **MySQL**.

<img width="480" height="382" alt="image" src="https://github.com/user-attachments/assets/db7076ab-e19a-4b6a-8bc2-af3cc3ff6ac5" />

## Features

- Passenger registration  
- Search buses by route (starting point → destination)  
- Book and cancel seats  
- Automatically update available seats  
- Simple console-based interaction

## Purpose

- Learn how to connect Java to MySQL using JDBC  
- Practice `PreparedStatement`, `ResultSet`, and CRUD operations (`SELECT`, `INSERT`, `UPDATE`, `DELETE`)  
- Understand database connectivity and SQL handling in Java

## Table Structures

**passanger_details**  
| Column       | Type         |
|--------------|-------------|
| username     | VARCHAR(30) |
| name         | VARCHAR(50) |
| age          | INT         |

**bus_details**  
| Column        | Type        |
|---------------|------------|
| busnumber     | INT        |
| starting      | VARCHAR(30)|
| destination   | VARCHAR(30)|
| avalable_seats| INT        |
| departue_time | TIME       |

**booking**  
| Column     | Type       |
|------------|-----------|
| username   | VARCHAR   |
| busnumber  | INT       |
| seats      | INT       |

## Setup

1. Install MySQL and create database `bustravellingagency`  
2. Create the tables above (DDL commands included in `/sql` folder if provided)  
3. Update `DriverConnection.java` with your DB credentials  
4. Run the project in IntelliJ or any Java IDE  

## Usage

- Register passengers first  
- Search for buses by route  
- Book seats (checks for availability)  
- Cancel bookings when needed  
- All actions are performed via console prompts  

## Notes

- Project is **for learning purposes only**  
- Fully dependent on JDBC; no frameworks used  
- Column names follow original spelling (`stating_point`, `avalable_seats`, etc.)  

## License

MIT License – free to use and modify for personal learning
