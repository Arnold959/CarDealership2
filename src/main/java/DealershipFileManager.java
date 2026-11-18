
import java.io.*;
import java.math.BigDecimal;

// Reads and saves the pipe-separated inventory.csv file.
public class DealershipFileManager {
    private final String fileName = "inventory.csv";

    public Dealership getDealership() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            // The first line describes the dealership.
            String line = reader.readLine();

            if (line == null) {
                return null;
            }

            String[] info = line.split("\\|", -1);

            Dealership dealership = new Dealership(
                    info[0], info[1], info[2]);

            // Every remaining line describes a vehicle.
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                Vehicle vehicle = new Vehicle(
                        Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        Integer.parseInt(data[6]),
                        new BigDecimal(data[7]));

                dealership.addVehicle(vehicle);
            }

            return dealership;

        } catch (IOException | RuntimeException e) {
            System.out.println("Could not read inventory.csv: " + e.getMessage());
            return null;
        }
    }

    // Save dealership information and vehicles
    public void saveDealership(Dealership dealership) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(fileName))) {

            writer.write(dealership.getName() + "|"
                    + dealership.getAddress() + "|"
                    + dealership.getPhone());

            writer.newLine();

            // Save every vehicle
            for (Vehicle vehicle : dealership.getAllVehicles()) {

                writer.write(vehicle.getVin() + "|"
                        + vehicle.getYear() + "|"
                        + vehicle.getMake() + "|"
                        + vehicle.getModel() + "|"
                        + vehicle.getVehicleType() + "|"
                        + vehicle.getColor() + "|"
                        + vehicle.getOdometer() + "|"
                        + vehicle.getPrice());

                writer.newLine();
            }

            System.out.println("Inventory saved.");

        } catch (IOException e) {
            System.out.println("Could not save inventory: " + e.getMessage());
        }
    }
}

