package ihfms.commands;

import ihfms.adapters.BillingSystemAdapter;
import ihfms.model.Invoice;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessInvoiceCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ProcessInvoiceCommand.class.getName());
    private Invoice invoice;
    private BillingSystemAdapter billingAdapter;

    public ProcessInvoiceCommand(Invoice invoice, BillingSystemAdapter billingAdapter) {
        this.invoice = invoice;
        this.billingAdapter = billingAdapter;
    }

    @Override
    public void execute() {
        try {
            billingAdapter.processInvoice(invoice);
            LOGGER.log(Level.INFO, "Invoice processing command executed for invoice ID: {0}", invoice.getInvoiceID());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error executing invoice processing command for invoice ID: {0}", invoice.getInvoiceID());
            // Handle the error appropriately
        }
    }
}
//package ihfms.commands;
//
//import ihfms.adapters.BillingSystemAdapter;
//import ihfms.model.Invoice;
//
//public class ProcessInvoiceCommand implements Command {
//    private Invoice invoice;
//    private BillingSystemAdapter billingAdapter;
//
//    public ProcessInvoiceCommand(Invoice invoice, BillingSystemAdapter billingAdapter)
//    {
//        this.invoice = invoice;
//        this.billingAdapter=billingAdapter;
//    }
//
//    @Override
//    public void execute() {
//        // Logic to process the invoice
//    }
//}