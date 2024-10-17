import java.util.ArrayList;
import java.util.List;

public class PaymentManager {
    private List<PaymentClass> paymentRecords = new ArrayList<>();

    // Method to record a payment
    public void recordPayment(PaymentClass payment) {
        paymentRecords.add(payment);
    }

    // Method to update payment status
    public void updatePaymentStatus(int paymentID, String newStatus) throws Exception {
        for (PaymentClass payment : paymentRecords) {
            if (payment.getPaymentID() == paymentID) {
                payment.setStatus(newStatus); // Assuming a setStatus method exists
                return;
            }
        }
        throw new Exception("Payment not found for ID: " + paymentID);
    }
}
