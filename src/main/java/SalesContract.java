
public class SalesContract extends Contract {

    // Additional sales information
    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private boolean finance;

    // Constructor
    public SalesContract(String date, String customerName,
                         String customerEmail, Vehicle vehicleSold,
                         boolean finance) {

        super(date, customerName, customerEmail, vehicleSold);

        this.finance = finance;

        // Get the vehicle price
        double price = vehicleSold.getPrice().doubleValue();

        // Calculate sales tax
        salesTax = price * 0.05;

        // Recording fee is always $100
        recordingFee = 100;

        // Processing fee depends on vehicle price
        if (price < 10000) {
            processingFee = 295;
        } else {
            processingFee = 495;
        }
    }

    // Getters and setters
    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    // Calculate total sales price
    @Override
    public double getTotalPrice() {

        double price = getVehicleSold().getPrice().doubleValue();

        return price + salesTax + recordingFee + processingFee;
    }

    // Calculate monthly payment if financing
    @Override
    public double getMonthlyPayment() {

        // No financing means no monthly payment
        if (!finance) {
            return 0;
        }

        double price = getVehicleSold().getPrice().doubleValue();

        double interestRate;
        int months;

        // Choose loan terms based on vehicle price
        if (price >= 10000) {
            interestRate = 0.0425;
            months = 48;
        } else {
            interestRate = 0.0525;
            months = 24;
        }

        // Convert annual interest to monthly interest
        double monthlyRate = interestRate / 12;

        // Standard monthly loan payment formula
        double total = getTotalPrice();

        double payment = total * monthlyRate /
                (1 - Math.pow(1 + monthlyRate, -months));

        return payment;
    }
}

