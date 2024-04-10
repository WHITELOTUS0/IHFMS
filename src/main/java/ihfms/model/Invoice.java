package ihfms.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private final StringProperty invoiceID = new SimpleStringProperty(this, "invoiceID");
    private final IntegerProperty patientID = new SimpleIntegerProperty(this, "patientID"); // Changed to IntegerProperty
    private final DoubleProperty amount = new SimpleDoubleProperty(this, "amount");
    private Date invoiceDate; // Keep as java.util.Date for now
    private List<Service> servicesProvided;

    public Invoice() {
        this.servicesProvided = new ArrayList<>();
    }

    // Method to add a service to the invoice
    public void addService(Service service) {
        servicesProvided.add(service);
    }

    // Method to calculate the total cost of the invoice
    public double calculateTotal() {
        double total = 0.0;
        for (Service service : servicesProvided) {
            total += service.getCost();
        }
        amount.set(total); // Update the amount property
        return total;
    }

    // Getters and setters
    public List<Service> getServicesProvided() {
        return servicesProvided;
    }

    public void setServicesProvided(List<Service> servicesProvided) {
        this.servicesProvided = servicesProvided;
    }

    // Getters and setters for properties
    public String getInvoiceID() { return invoiceID.get(); }
    public void setInvoiceID(String value) { invoiceID.set(value); }
    public StringProperty invoiceIDProperty() { return invoiceID; }

    public int getPatientID() { return patientID.get(); }
    public void setPatientID(int value) { patientID.set(value); }
    public IntegerProperty patientIDProperty() { return patientID; }

    public double getAmount() { return amount.get(); }
    public void setAmount(double value) { amount.set(value); }
    public DoubleProperty amountProperty() { return amount; }

    // Getters and setters for other fields
    public Date getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(Date invoiceDate) { this.invoiceDate = invoiceDate; }
}