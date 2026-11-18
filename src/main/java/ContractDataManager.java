
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContractDataManager {

    // File where all contracts will be saved
    private String fileName = "contracts.csv";

    // Save a sale or lease contract
    public boolean saveContract(Contract contract) {

        // Check that the contract exists
        if (contract == null || contract.getVehicleSold() == null) {
            System.out.println("Invalid contract.");
            return false;
        }

        try {
            // true means append instead of overwrite
            FileWriter fileWriter = new FileWriter(fileName, true);
            PrintWriter writer = new PrintWriter(fileWriter);

            Vehicle vehicle = contract.getVehicleSold();

            // Save information shared by both contract types
            String data = "";

            // Identify the contract type
            if (contract instanceof SalesContract) {
                data = "SALE|";
            } else if (contract instanceof LeaseContract) {
                data = "LEASE|";
            } else {
                writer.close();
                return false;
            }

            // Customer information
            data += contract.getDate() + "|";
            data += contract.getCustomerName() + "|";
            data += contract.getCustomerEmail() + "|";

            // Vehicle information
            data += vehicle.getVin() + "|";
            data += vehicle.getYear() + "|";
            data += vehicle.getMake() + "|";
            data += vehicle.getModel() + "|";
            data += vehicle.getVehicleType() + "|";
            data += vehicle.getColor() + "|";
            data += vehicle.getOdometer() + "|";
            data += vehicle.getPrice() + "|";

            // Save additional sales information
            if (contract instanceof SalesContract) {

                SalesContract sale = (SalesContract) contract;

                data += String.format(java.util.Locale.US,
                        "%.2f|", sale.getSalesTax());
                data += String.format(java.util.Locale.US,
                        "%.2f|", sale.getRecordingFee());
                data += String.format(java.util.Locale.US,
                        "%.2f|", sale.getProcessingFee());
                data += String.format(java.util.Locale.US,
                        "%.2f|", sale.getTotalPrice());

                // Save financing choice
                if (sale.isFinance()) {
                    data += "YES|";
                } else {
                    data += "NO|";
                }

                data += String.format(java.util.Locale.US,
                        "%.2f", sale.getMonthlyPayment());
            }

            // Save additional lease information
            else if (contract instanceof LeaseContract) {

                LeaseContract lease = (LeaseContract) contract;

                data += String.format(java.util.Locale.US,
                        "%.2f|", lease.getExpectedEndingValue());
                data += String.format(java.util.Locale.US,
                        "%.2f|", lease.getLeaseFee());
                data += String.format(java.util.Locale.US,
                        "%.2f|", lease.getTotalPrice());
                data += String.format(java.util.Locale.US,
                        "%.2f", lease.getMonthlyPayment());
            }

            // Write one contract on a new line
            writer.println(data);

            // Check for writing errors
            boolean success = !writer.checkError();

            writer.close();

            if (success) {
                System.out.println("Contract saved successfully!");
            } else {
                System.out.println("Error saving contract.");
            }

            return success;

        } catch (IOException e) {
            System.out.println("Error saving contract: "
                    + e.getMessage());
            return false;
        }
    }
}

