package ihfms.adapters;

import ihfms.external.ExternalBillingSystem;
import ihfms.model.Invoice;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillingAdapter implements BillingSystemAdapter {
    private static final Logger LOGGER = Logger.getLogger(BillingAdapter.class.getName());
    private ExternalBillingSystem externalBillingSystem;

    public BillingAdapter(ExternalBillingSystem externalBillingSystem) {
        this.externalBillingSystem = externalBillingSystem;
    }

    @Override
    public void processInvoice(Invoice invoice) {
        try {
            // Convert your Invoice object to the format expected by the external system
            externalBillingSystem.createInvoice(invoice.getPatientID(), invoice.getAmount());
            LOGGER.log(Level.INFO, "Invoice processed for patient ID: {0}", invoice.getPatientID());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing invoice for patient ID: {0}", invoice.getPatientID());
            // Handle the error appropriately
        }
    }
}
//package ihfms.adapters;
//
//import ihfms.external.ExternalBillingSystem;
//import ihfms.model.Invoice;
//
//public class BillingAdapter implements BillingSystemAdapter {
//    // Assume ExternalBillingSystem is a class from an external library
//    private ExternalBillingSystem externalBillingSystem;
//
//    public BillingAdapter(ExternalBillingSystem externalBillingSystem) {
//        this.externalBillingSystem = externalBillingSystem;
//    }
//
//    @Override
//    public void processInvoice(Invoice invoice) {
//        // Convert your Invoice object to the format expected by the external system
//        externalBillingSystem.createInvoice(invoice.getPatientID(), invoice.getAmount());
//    }
//}