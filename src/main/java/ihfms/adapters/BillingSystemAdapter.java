package ihfms.adapters;
import ihfms.model.Invoice;

public interface BillingSystemAdapter {
    void processInvoice(Invoice invoice);
}
