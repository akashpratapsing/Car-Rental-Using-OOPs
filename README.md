# Car Rental System

This Java project implements a simple car rental system using Object-Oriented Programming (OOP) concepts.

## Features:

- Manages a collection of cars with attributes like brand, model, base price per day, and availability.
- Creates customer profiles with customer ID and name.
- Rents cars to customers for a specified number of days.
- Calculates the total rental price based on the base price and rental days.
- Allows customers to return rented cars.
- Provides a user-friendly menu for interaction (renting, returning, exiting).

## Getting Started

1. Clone this repository to your local machine.
2. Ensure you have Java Development Kit (JDK) installed and configured on your system (refer to https://www.oracle.com/java/technologies/downloads/ for installation instructions).
3. Open the project in your preferred IDE (e.g., Eclipse, IntelliJ IDEA).
4. Run the `Main` class to start the car rental system application.

## Dependencies

This project does not require any external libraries beyond the Java standard library.

## Usage

The application presents a menu-driven interface for interacting with the car rental system. You can perform the following actions:

1. **Rent a car:**
  - Enter your name to create a customer profile.
  - View available cars (brand, model).
  - Select a car by ID and specify the rental duration.
  - Confirm the rental to finalize the process.

2. **Return Car:**
  - Enter the car ID you wish to return.
  - The system verifies the car's rental status and displays the customer who rented it (if applicable).
  - Confirm the return to update the car's availability.

3. **Exit:**
  - Terminate the application.

## Further Enhancements

- Implement user authentication for customer accounts.
- Integrate persistence mechanisms (e.g., databases) to store car and customer data permanently.
- Add features like car search based on brand, model, or price range.
- Introduce different car categories with varying price ranges.

Feel free to explore the code and customize it to add more functionalities!
