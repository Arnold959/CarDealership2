
import java.util.ArrayList;

// Stores dealership details and searches its vehicles.
public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public ArrayList<Vehicle> getAllVehicles() { return inventory; }

    // Search vehicles by price
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            double price = vehicle.getPrice().doubleValue();

            if (price >= min && price <= max) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    // Search vehicles by make and model
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make)
                    && vehicle.getModel().equalsIgnoreCase(model)) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    // Search vehicles by year
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    // Search vehicles by color
    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    // Search vehicles by mileage
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min
                    && vehicle.getOdometer() <= max) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    // Search vehicles by type
    public ArrayList<Vehicle> getVehiclesByType(String type) {
        ArrayList<Vehicle> matches = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
                matches.add(vehicle);
            }
        }
        return matches;
    }

    // Add a vehicle
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    // Remove a vehicle
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    // Find a vehicle using its VIN
    public Vehicle getVehicleByVin(int vin) {

        // Check every vehicle in the inventory
        for (Vehicle vehicle : inventory) {

            if (vehicle.getVin() == vin) {
                return vehicle;
            }
        }

        // Return null if the vehicle was not found
        return null;
    }

}

