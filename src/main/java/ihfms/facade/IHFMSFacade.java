package ihfms.facade;

import ihfms.adapters.BillingSystemAdapter;
import ihfms.model.Invoice;
import ihfms.model.Patient;

public class IHFMSFacade {
    private BillingSystemAdapter billingAdapter;

    public IHFMSFacade(BillingSystemAdapter billingAdapter) {
        this.billingAdapter = billingAdapter;
    }

    public void processPatientInvoice(Patient patient, Invoice invoice) {
        billingAdapter.processInvoice(invoice);
        // Additional logic to notify the patient or update the system state
    }
}
