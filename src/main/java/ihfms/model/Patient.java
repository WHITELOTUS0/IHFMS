package ihfms.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Date;

public class Patient {
    private final StringProperty patientID = new SimpleStringProperty(this, "patientID");
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
    private Date dateOfBirth; // Keep as java.util.Date for now
    private String gender; // Could also be refactored to use a Property if needed
    private String contactInfo; // Could also be refactored to use a Property if needed

    // Getters and setters for properties
    public String getPatientID() { return patientID.get(); }
    public void setPatientID(String value) { patientID.set(value); }
    public StringProperty patientIDProperty() { return patientID; }

    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String value) { firstName.set(value); }
    public StringProperty firstNameProperty() { return firstName; }

    public String getLastName() { return lastName.get(); }
    public void setLastName(String value) { lastName.set(value); }
    public StringProperty lastNameProperty() { return lastName; }

    // Getters and setters for other fields
    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
}
//package ihfms.model;
//
//import java.util.Date;
//
//public class Patient {
//    private String patientID;
//    private String firstName;
//    private String lastName;
//    private Date dateOfBirth;
//    private String gender;
//    private String contactInfo;
//
//    // Getters
//    public String getPatientID() {
//        return patientID;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public Date getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public String getContactInfo() {
//        return contactInfo;
//    }
//
//    // Setters
//    public void setPatientID(String patientID) {
//        this.patientID = patientID;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public void setDateOfBirth(Date dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public void setContactInfo(String contactInfo) {
//        this.contactInfo = contactInfo;
//    }
//}