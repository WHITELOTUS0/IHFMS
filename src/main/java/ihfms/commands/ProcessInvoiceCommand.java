package ihfms.commands;

import ihfms.model.Invoice;

public class ProcessInvoiceCommand implements Command {
    private Invoice invoice;

    public ProcessInvoiceCommand(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public void execute() {
        // Logic to process the invoice
    }
}