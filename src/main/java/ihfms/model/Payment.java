package ihfms.model;

import java.util.Date;

public class Payment {
    private String paymentID;
    private String invoiceID;
    private double amountPaid;
    private Date paymentDate;

    // Getters
    public String getPaymentID() {
        return paymentID;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    // Setters
    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}