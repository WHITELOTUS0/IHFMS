import ihfms.adapters.BillingSystemAdapter;
import ihfms.commands.ProcessInvoiceCommand;
import ihfms.model.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class ProcessInvoiceCommandTest {
    private BillingSystemAdapter billingAdapter;
    private ProcessInvoiceCommand processInvoiceCommand;
    private Invoice invoice;

    @BeforeEach
    public void setUp() {
        billingAdapter = Mockito.mock(BillingSystemAdapter.class);
        invoice = new Invoice();
        invoice.setInvoiceID(1);
        processInvoiceCommand = new ProcessInvoiceCommand(invoice, billingAdapter);
    }

    @Test
    public void testExecute() {
        processInvoiceCommand.execute();
        verify(billingAdapter).processInvoice(invoice);
    }
}