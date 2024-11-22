import java.util.Scanner;

// Define the target interface
interface PaymentGatewayAdapter {
    void processPayment(double amountInBDT);
}

// Third-party payment gateway (adaptee)
class ThirdPartyPaymentGateway {
    public void processPaymentInUSD(double amountInUSD) {
        System.out.println("Payment processed successfully in USD: $" + amountInUSD);
    }
}

// Adapter class to integrate the third-party payment gateway
class PaymentGatewayAdapterImpl implements PaymentGatewayAdapter {
    private ThirdPartyPaymentGateway thirdPartyPaymentGateway;

    public PaymentGatewayAdapterImpl(ThirdPartyPaymentGateway thirdPartyPaymentGateway) {
        this.thirdPartyPaymentGateway = thirdPartyPaymentGateway;
    }

    @Override
    public void processPayment(double amountInBDT) {
        // Assuming a conversion rate of 1 USD = 85 BDT
        double conversionRate = 85.0;
        double amountInUSD = amountInBDT / conversionRate;

        // Pass the converted amount to the third-party gateway
        thirdPartyPaymentGateway.processPaymentInUSD(amountInUSD);
    }
}

// Main class for testing the payment plugin
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the third-party payment gateway
        ThirdPartyPaymentGateway thirdPartyPaymentGateway = new ThirdPartyPaymentGateway();

        // Create the adapter
        PaymentGatewayAdapter paymentGateway = new PaymentGatewayAdapterImpl(thirdPartyPaymentGateway);

        // Take input from the user
        System.out.print("Enter the amount to be paid in BDT: ");
        double amountInBDT = scanner.nextDouble();

        // Process the payment
        System.out.println("Processing payment...");
        paymentGateway.processPayment(amountInBDT);

        // Close the scanner
        scanner.close();
    }
}