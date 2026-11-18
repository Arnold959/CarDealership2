
public class LeaseContract extends Contract {

    // Additional lease information
    private double expectedEndingValue;
    private double leaseFee;

    // Constructor
    public LeaseContract(String date, String customerName,
                         String customerEmail, Vehicle vehicleSold) {

        super(date, customerName, customerEmail, vehicleSold);

        // Get the original vehicle price
        double price = vehicleSold.getPrice().doubleValue();

        // Calculate expected ending value (50%)
        expectedEndingValue = price * 0.50;

        // Calculate lease fee (7%)
        leaseFee = price * 0.07;
    }

    // Getters and setters
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    // Calculate total lease price
    @Override
    public double getTotalPrice() {

        return expectedEndingValue + leaseFee;
    }

    // Calculate monthly lease payment
    @Override
    public double getMonthlyPayment() {

        double total = getTotalPrice();

        // Annual interest rate is 4%
        double interestRate = 0.04;

        // Lease lasts 36 months
        int months = 36;

        // Convert annual rate to monthly rate
        double monthlyRate = interestRate / 12;

        // Calculate monthly payment
        double payment = total * monthlyRate /
                (1 - Math.pow(1 + monthlyRate, -months));

        return payment;
    }
}
