package ihfms.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import java.util.Date;

public class Invoice {
    private final StringProperty invoiceID = new SimpleStringProperty(this, "invoiceID");
    private final StringProperty patientID = new SimpleStringProperty(this, "patientID");
    private final DoubleProperty amount = new SimpleDoubleProperty(this, "amount");
    private Date invoiceDate; // Keep as java.util.Date for now

    // Getters and setters for properties
    public String getInvoiceID() { return invoiceID.get(); }
    public void setInvoiceID(String value) { invoiceID.set(value); }
    public StringProperty invoiceIDProperty() { return invoiceID; }

    public String getPatientID() { return patientID.get(); }
    public void setPatientID(String value) { patientID.set(value); }
    public StringProperty patientIDProperty() { return patientID; }

    public double getAmount() { return amount.get(); }
    public void setAmount(double value) { amount.set(value); }
    public DoubleProperty amountProperty() { return amount; }

    // Getters and setters for other fields
    public Date getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(Date invoiceDate) { this.invoiceDate = invoiceDate; }
}
//package ihfms.model;
//
//import java.util.Date;
//
//public class Invoice {
//    private String invoiceID;
//    private String patientID;
//    private double amount;
//    private Date invoiceDate;
//
//    // Getters
//    public String getInvoiceID() {
//        return invoiceID;
//    }
//
//    public String getPatientID() {
//        return patientID;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public Date getInvoiceDate() {
//        return invoiceDate;
//    }
//
//    // Setters
//    public void setInvoiceID(String invoiceID) {
//        this.invoiceID = invoiceID;
//    }
//
//    public void setPatientID(String patientID) {
//        this.patientID = patientID;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public void setInvoiceDate(Date invoiceDate) {
//        this.invoiceDate = invoiceDate;
//    }
//}