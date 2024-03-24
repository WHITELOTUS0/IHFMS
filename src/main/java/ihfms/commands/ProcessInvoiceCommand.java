package ihfms.commands;

import ihfms.adapters.BillingSystemAdapter;
import ihfms.model.Invoice;

public class ProcessInvoiceCommand implements Command {
    private Invoice invoice;
    private BillingSystemAdapter billingAdapter;

    public ProcessInvoiceCommand(Invoice invoice, BillingSystemAdapter billingAdapter)
    {
        this.invoice = invoice;
        this.billingAdapter=billingAdapter;
    }

    @Override
    public void execute() {
        // Logic to process the invoice
    }
}