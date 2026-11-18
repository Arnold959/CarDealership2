
# Car Dealership Management System

## Project Description

The Car Dealership Management System is a Java console application that allows a car dealership to manage its vehicle inventory, search for vehicles, and process vehicle sales and leases.

The project was developed using Object-Oriented Programming (OOP) principles and demonstrates how Java classes, methods, inheritance, polymorphism, abstraction, and file handling work together in a real-world application.

The application stores vehicle information in `inventory.csv` and saves sales and lease transactions in `contracts.csv`.

This project was completed in two parts:

- Part 1: Vehicle Inventory Management
- Part 2: Vehicle Sales and Leasing

## Features

### 1. Vehicle Inventory Management

The application allows users to:

- View all available vehicles.
- Search for vehicles by price range.
- Search for vehicles by make and model.
- Search for vehicles by year range.
- Search for vehicles by color.
- Search for vehicles by mileage range.
- Search for vehicles by vehicle type.
- Add new vehicles to the inventory.
- Remove vehicles from the inventory.

### 2. Vehicle Sales

Users can sell vehicles by:

1. Entering the vehicle VIN.
2. Providing customer information.
3. Selecting the sales option.
4. Choosing whether to finance the vehicle.
5. Viewing the total sales price and monthly payment.
6. Confirming the transaction.

The application calculates:

- Sales tax: 5% of vehicle price.
- Recording fee: $100.
- Processing fee: $295 for vehicles under $10,000.
- Processing fee: $495 for vehicles priced at $10,000 or more.

Financing options:

- Vehicles under $10,000: 5.25% annual interest for 24 months.
- Vehicles priced at $10,000 or more: 4.25% annual interest for 48 months.

After a successful sale, the contract is saved and the vehicle is removed from inventory.

### 3. Vehicle Leasing

Users can lease eligible vehicles by:

1. Entering the vehicle VIN.
2. Providing customer information.
3. Selecting the lease option.
4. Checking vehicle eligibility.
5. Viewing the lease price and monthly payment.
6. Confirming the transaction.

Leasing rules:

- Vehicles older than three years cannot be leased.
- Expected ending value is 50% of the vehicle price.
- Lease fee is 7% of the vehicle price.
- Lease financing uses 4% annual interest over 36 months.

After a successful lease, the contract is saved and the vehicle is removed from inventory.

### 4. File Management

The application uses two CSV files:

**inventory.csv**

Stores dealership information and available vehicles.

**contracts.csv**

Stores completed sales and lease contracts.

Both files use the pipe (`|`) character to separate values.

New contracts are appended to `contracts.csv` without overwriting existing records.

## Technologies Used

- Java
- IntelliJ IDEA
- Maven
- CSV Files
- Git
- GitHub

## Java Concepts Demonstrated

### Object-Oriented Programming (OOP)

The project uses classes and objects to represent vehicles, dealerships, and contracts.

### Encapsulation

Class properties are private and accessed through getters and setters.

### Inheritance

`SalesContract` and `LeaseContract` inherit common properties and methods from the abstract `Contract` class.

### Abstraction

The abstract `Contract` class defines common contract information and abstract methods for calculating prices.

### Polymorphism

The application uses a `Contract` reference to work with both sales and lease contracts.

Each subclass provides its own implementation of:

- `getTotalPrice()`
- `getMonthlyPayment()`

### Constructors

Constructors initialize objects with the information needed to represent vehicles, dealerships, and contracts.

### Methods

Methods organize the application into smaller, reusable sections.

### Conditional Statements

`if`, `else if`, `else`, and `switch` statements control program behavior.

### Loops

Loops allow the application to display menus repeatedly and search through vehicle inventory.

### ArrayList

An `ArrayList` stores and manages the dealership's vehicle inventory.

### File Handling

`FileWriter`, `PrintWriter`, and file-reading operations allow the application to store and retrieve information.

### Exception Handling

`try` and `catch` blocks help handle errors during file operations.

## Project Structure

```text
CarDealership/
|
|-- pom.xml
|-- README.md
|-- inventory.csv
|-- contracts.csv
|
|-- src/
    |-- main/
        |-- java/
            |-- Program.java
            |-- Vehicle.java
            |-- Dealership.java
            |-- DealershipFileManager.java
            |-- UserInterface.java
            |-- Contract.java
            |-- SalesContract.java
            |-- LeaseContract.java
            |-- ContractDataManager.java
```

## Class Descriptions

### Program.java

Contains the `main()` method and starts the application.

### Vehicle.java

Represents a vehicle and stores its VIN, year, make, model, type, color, mileage, and price.

### Dealership.java

Stores dealership information and manages the vehicle inventory.

### DealershipFileManager.java

Loads vehicle information from `inventory.csv` and saves inventory changes.

### UserInterface.java

Displays the application menu, accepts user input, and processes user requests.

### Contract.java

An abstract parent class containing information shared by sales and lease contracts.

### SalesContract.java

Extends `Contract` and calculates sales taxes, fees, total prices, and financing payments.

### LeaseContract.java

Extends `Contract` and calculates lease fees, expected ending values, and monthly payments.

### ContractDataManager.java

Saves completed sales and lease contracts to `contracts.csv`.

## How to Run the Application

### Requirements

- Java Development Kit (JDK)
- IntelliJ IDEA
- Maven

### Instructions

1. Clone or download this repository.
2. Open IntelliJ IDEA.
3. Select **Open**.
4. Select the CarDealership project folder.
5. Allow IntelliJ to load the Maven project.
6. Confirm that `inventory.csv` and `contracts.csv` are in the main project folder.
7. Open `Program.java`.
8. Click the Run button next to the `main()` method.
9. Follow the instructions displayed in the console.

Make sure the working directory is the project root so the application can find its CSV files.

## Application Menu

```text
1  - Find vehicles by price range
2  - Find vehicles by make/model
3  - Find vehicles by year range
4  - Find vehicles by color
5  - Find vehicles by mileage range
6  - Find vehicles by type
7  - List all vehicles
8  - Add a vehicle
9  - Remove a vehicle
10 - Sell/Lease a Vehicle
99 - Quit
```

## Testing

The application was manually tested using IntelliJ IDEA.

### Test 1: Display Vehicle Inventory

**Steps:**

1. Run the application.
2. Select option 7.
3. View the available vehicles.

**Expected Result:**

The application displays the vehicles stored in `inventory.csv`.

**Result:** Passed.

### Test 2: Add a Vehicle

**Steps:**

1. Select option 8.
2. Enter the vehicle information.
3. Return to the main menu.
4. Select option 7.

**Expected Result:**

The newly added vehicle appears in the inventory and is saved to `inventory.csv`.

**Result:** Passed.

### Test 3: Sell a Vehicle

**Steps:**

1. Select option 10.
2. Enter the VIN of an available vehicle.
3. Enter the customer's name and email.
4. Select the sales option.
5. Choose whether to finance.
6. Review the calculated sales price.
7. Confirm the transaction.

**Expected Result:**

- The application calculates the sales price.
- The contract is saved to `contracts.csv`.
- The vehicle is removed from inventory.
- The updated inventory is saved to `inventory.csv`.

**Result:** Passed.

### Test 4: Lease a Vehicle

**Steps:**

1. Select option 10.
2. Enter the VIN of an eligible vehicle.
3. Enter the customer's information.
4. Select the lease option.
5. Review the calculated lease price.
6. Confirm the transaction.

**Expected Result:**

- The application checks vehicle eligibility.
- The lease price and monthly payment are calculated.
- The contract is saved to `contracts.csv`.
- The leased vehicle is removed from inventory.

**Result:** Passed.

### Test 5: Lease an Older Vehicle

**Steps:**

1. Select option 10.
2. Enter the VIN of a vehicle older than three years.
3. Select the lease option.

**Expected Result:**

The application displays a message explaining that the vehicle is too old to lease.

The vehicle remains in inventory.

### Test 6: Invalid VIN

**Steps:**

1. Select option 10.
2. Enter a VIN that does not exist.

**Expected Result:**

The application displays "Vehicle not found" and returns to the menu without creating a contract.

### Test 7: Contract File Verification

**Steps:**

1. Complete a sale or lease.
2. Open `contracts.csv`.
3. Check the saved information.

**Expected Result:**

- The new contract appears at the end of the file.
- Existing contracts remain unchanged.
- Contract information is separated using the pipe (`|`) delimiter.

**Result:** Passed.

## Challenges and Lessons Learned

One of the main challenges was connecting multiple Java classes while ensuring that they worked together correctly.

I learned how to:

- Organize a Java application using separate classes.
- Use inheritance to avoid repeating code.
- Apply polymorphism when processing different contract types.
- Calculate sales and lease payments.
- Save and retrieve data using CSV files.
- Update vehicle inventory after completed transactions.
- Test application features and debug errors.

This project strengthened my understanding of Object-Oriented Programming and how Java can be used to build practical business applications.

## Future Improvements

Possible future improvements include:

- Adding an administrator login system.
- Allowing users to view previous contracts.
- Adding more detailed input validation.
- Generating customer receipts.
- Adding a graphical user interface.
- Connecting the application to a database.

## Project Status

Completed - Car Dealership Workshop Parts 1 and 2.

Core features include inventory management, vehicle searching, sales processing, lease processing, contract calculations, and CSV file storage.

## Author

Developed as part of a Java programming workshop to demonstrate beginner-level Java programming and Object-Oriented Programming skills.
