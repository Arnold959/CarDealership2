import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


// Shows the menu and handles keyboard input.
public class UserInterface {

    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);
    private DealershipFileManager fileManager = new DealershipFileManager();

    // Load the dealership from the CSV file
    private void init() {
        dealership = fileManager.getDealership();
    }

    // Used to save sales and lease contracts
    private ContractDataManager contractManager =
            new ContractDataManager();


    // Display the main menu
    public void display() {

        init();

        if (dealership == null) {
            System.out.println("Check that inventory.csv is in the project folder.");
            return;
        }

        int choice = 0;

        while (choice != 99) {

            System.out.println("\n=== " + dealership.getName() + " ===");
            System.out.println("1 - Find vehicles by price range");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type");
            System.out.println("7 - List all vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("10 - Sell/Lease a Vehicle");
            System.out.println("99 - Quit");

            choice = readInt("Choose an option: ");

            switch (choice) {
                case 1: processGetByPriceRequest(); break;
                case 2: processGetByMakeModelRequest(); break;
                case 3: processGetByYearRequest(); break;
                case 4: processGetByColorRequest(); break;
                case 5: processGetByMileageRequest(); break;
                case 6: processGetByVehicleTypeRequest(); break;
                case 7: processGetAllVehiclesRequest(); break;
                case 8: processAddVehicleRequest(); break;
                case 9: processRemoveVehicleRequest(); break;
                case 10:processSellLeaseVehicleRequest();break;
                case 99: System.out.println("Goodbye!"); break;
                default: System.out.println("Please choose a menu option.");
            }
        }
    }

    // Read a whole number safely
    private int readInt(String question) {
        while (true) {
            System.out.print(question);

            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a whole number.");
            }
        }
    }

    // Read a decimal number safely
    private double readDouble(String question) {
        while (true) {
            System.out.print(question);

            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a number.");
            }
        }
    }

    // Read a vehicle price
    private BigDecimal readPrice(String question) {
        while (true) {
            System.out.print(question);

            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid price.");
            }
        }
    }

    // Display vehicles returned by a search
    private void displayVehicles(ArrayList<Vehicle> vehicles) {

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    // Option 1 - Search by price
    private void processGetByPriceRequest() {

        double min = readDouble("Minimum price: ");
        double max = readDouble("Maximum price: ");

        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    // Option 2 - Search by make and model
    private void processGetByMakeModelRequest() {

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    // Option 3 - Search by year
    private void processGetByYearRequest() {

        int min = readInt("Minimum year: ");
        int max = readInt("Maximum year: ");

        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    // Option 4 - Search by color
    private void processGetByColorRequest() {

        System.out.print("Color: ");
        String color = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByColor(color));
    }

    // Option 5 - Search by mileage
    private void processGetByMileageRequest() {

        int min = readInt("Minimum mileage: ");
        int max = readInt("Maximum mileage: ");

        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    // Option 6 - Search by vehicle type
    private void processGetByVehicleTypeRequest() {

        System.out.print("Type (car, truck, SUV, van): ");
        String type = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByType(type));
    }

    // Option 7 - Display all vehicles
    private void processGetAllVehiclesRequest() {

        displayVehicles(dealership.getAllVehicles());
    }

    // Option 8 - Add a vehicle
    private void processAddVehicleRequest() {

        int vin = readInt("VIN: ");

        // Check if VIN already exists
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                System.out.println("That VIN already exists.");
                return;
            }
        }

        int year = readInt("Year: ");

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        int mileage = readInt("Mileage: ");
        BigDecimal price = readPrice("Price: ");

        // Create a vehicle object
        Vehicle vehicle = new Vehicle(
                vin, year, make, model,
                type, color, mileage, price);

        // Add vehicle to inventory
        dealership.addVehicle(vehicle);

        // Save changes to inventory.csv
        fileManager.saveDealership(dealership);

        System.out.println("Vehicle added.");
    }

    // Option 9 - Remove a vehicle
    private void processRemoveVehicleRequest() {

        int vin = readInt("VIN to remove: ");

        for (Vehicle vehicle : dealership.getAllVehicles()) {

            if (vehicle.getVin() == vin) {

                dealership.removeVehicle(vehicle);

                // Save changes to inventory.csv
                fileManager.saveDealership(dealership);

                System.out.println("Vehicle removed.");
                return;
            }
        }

        System.out.println("Vehicle not found.");
    }

    // Option 10 - Sell or lease a vehicle
    private void processSellLeaseVehicleRequest() {

        // Ask for the vehicle VIN
        int vin = readInt("Enter vehicle VIN: ");

        // Find the vehicle in the inventory
        Vehicle vehicle = dealership.getVehicleByVin(vin);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        // Display the selected vehicle
        System.out.println("\nSelected vehicle:");
        System.out.println(vehicle);

        // Ask for customer information
        System.out.print("Customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Customer email: ");
        String customerEmail = scanner.nextLine();

        // Get today's date in YYYYMMDD format
        String date = LocalDate.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd"));

        // Ask whether to sell or lease
        System.out.println("\n1 - Sell Vehicle");
        System.out.println("2 - Lease Vehicle");

        int choice = readInt("Enter your choice: ");

        // Parent class reference
        Contract contract;

        if (choice == 1) {

            // Ask whether the customer wants financing
            System.out.print("Finance vehicle? (yes/no): ");
            String answer = scanner.nextLine();

            boolean finance = answer.equalsIgnoreCase("yes");

            // Create a sales contract
            contract = new SalesContract(
                    date,
                    customerName,
                    customerEmail,
                    vehicle,
                    finance
            );

        } else if (choice == 2) {

            // Vehicles older than 3 years cannot be leased
            int currentYear = LocalDate.now().getYear();

            if (currentYear - vehicle.getYear() > 3) {
                System.out.println(
                        "This vehicle is too old to lease.");
                return;
            }

            // Create a lease contract
            contract = new LeaseContract(
                    date,
                    customerName,
                    customerEmail,
                    vehicle
            );

        } else {
            System.out.println("Invalid contract choice.");
            return;
        }

        // Display the contract details
        System.out.println("\n=== CONTRACT DETAILS ===");

        System.out.println("Customer: "
                + contract.getCustomerName());

        System.out.println("Vehicle: "
                + vehicle.getYear() + " "
                + vehicle.getMake() + " "
                + vehicle.getModel());

        System.out.printf("Total Price: $%.2f%n",
                contract.getTotalPrice());

        System.out.printf("Monthly Payment: $%.2f%n",
                contract.getMonthlyPayment());

        // Ask for confirmation
        System.out.print("\nComplete transaction? (yes/no): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Transaction cancelled.");
            return;
        }

        // Save the contract first
        boolean saved = contractManager.saveContract(contract);

        if (!saved) {
            System.out.println("Transaction was not completed.");
            return;
        }

        // Remove the sold or leased vehicle
        dealership.removeVehicle(vehicle);

        // Save the updated inventory
        fileManager.saveDealership(dealership);

        System.out.println("Transaction completed!");
    }

}


