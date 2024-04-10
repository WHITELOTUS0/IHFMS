package ihfms.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import java.util.Date;

public class Patient {
    private final IntegerProperty patientID = new SimpleIntegerProperty(this, "patientID");
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
    private Date dateOfBirth; // Keep as java.util.Date for now
    private String gender; // Could also be refactored to use a Property if needed
    private String contactInfo; // Could also be refactored to use a Property if needed
    private final StringProperty phoneNumber = new SimpleStringProperty(this, "phoneNumber");
    private final StringProperty email = new SimpleStringProperty(this, "email");

    // Getters and setters for properties
    public int getPatientID() { return patientID.get(); }
    public void setPatientID(int value) { patientID.set(value); }
    public IntegerProperty patientIDProperty() { return patientID; }

    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String value) { firstName.set(value); }
    public StringProperty firstNameProperty() { return firstName; }

    public String getLastName() { return lastName.get(); }
    public void setLastName(String value) { lastName.set(value); }
    public StringProperty lastNameProperty() { return lastName; }

    public String getPhoneNumber() { return phoneNumber.get(); }
    public void setPhoneNumber(String value) { phoneNumber.set(value); }
    public StringProperty phoneNumberProperty() { return phoneNumber; }

    public String getEmail() { return email.get(); }
    public void setEmail(String value) { email.set(value); }
    public StringProperty emailProperty() { return email; }

    // Getters and setters for other fields
    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
}