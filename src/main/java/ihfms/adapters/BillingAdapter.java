package ihfms.adapters;

import ihfms.model.Invoice;

public class BillingAdapter implements BillingSystemAdapter {
    // Assume ExternalBillingSystem is a class from an external library
    private ExternalBillingSystem externalBillingSystem;

    public BillingAdapter(ExternalBillingSystem externalBillingSystem) {
        this.externalBillingSystem = externalBillingSystem;
    }

    @Override
    public void processInvoice(Invoice invoice) {
        // Convert your Invoice object to the format expected by the external system
        externalBillingSystem.createInvoice(invoice.getPatientID(), invoice.getAmount());
    }
}