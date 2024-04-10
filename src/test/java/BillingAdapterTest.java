
import ihfms.adapters.BillingAdapter;
import ihfms.external.ExternalBillingSystem;
import ihfms.model.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class BillingAdapterTest {
    private ExternalBillingSystem externalBillingSystem;
    private BillingAdapter billingAdapter;
    private Invoice invoice;

    @BeforeEach
    public void setUp() {
        externalBillingSystem = Mockito.mock(ExternalBillingSystem.class);
        billingAdapter = new BillingAdapter(externalBillingSystem);
        invoice = new Invoice();
        invoice.setPatientID(1);
        invoice.setAmount(100.0);
    }

    @Test
    public void testProcessInvoice() {
        billingAdapter.processInvoice(invoice);
        verify(externalBillingSystem).createInvoice(invoice.getPatientID(), invoice.getAmount());
    }
}