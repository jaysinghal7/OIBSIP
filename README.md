# Online Reservation System

A GUI-based Train Reservation System developed using Java Swing, JDBC and SQLite.

## Features

- User Login
- Invalid login protection
- Train reservation
- Automatic train name lookup
- Class selection
- Journey date validation
- Automatic PNR generation
- Booking confirmation
- PNR-based booking search
- Ticket cancellation
- Confirmation dialog before cancellation
- SQLite database
- PreparedStatement for database operations

## Technologies

- Java 17
- Java Swing
- JDBC
- SQLite
- Maven

## Default Login

Username:

admin

Password:

admin123

## Sample Train Numbers

| Train Number | Train Name |
|--------------|------------|
| 12301 | Rajdhani Express |
| 12302 | Kolkata Rajdhani |
| 12002 | Bhopal Shatabdi |
| 12951 | Mumbai Rajdhani |
| 12555 | Gorakhpur Express |

## How to Run

### Using IntelliJ IDEA

1. Clone/download the repository.
2. Open the project in IntelliJ IDEA.
3. Wait for Maven dependencies to download.
4. Open `Main.java`.
5. Run the program.

### Using Command Line

```bash
mvn clean compile