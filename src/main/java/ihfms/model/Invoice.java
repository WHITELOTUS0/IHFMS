package ihfms.model;

import java.util.Date;

public class Invoice {
    private String invoiceID;
    private String patientID;
    private double amount;
    private Date invoiceDate;

    // Getters
    public String getInvoiceID() {
        return invoiceID;
    }

    public String getPatientID() {
        return patientID;
    }

    public double getAmount() {
        return amount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    // Setters
    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}