package ihfms.model;

public class MedicalRecord {
    private String recordID;
    private String patientID;
    private String diagnosis;
    private String medications;
    private String treatments;

    // Getters
    public String getRecordID() {
        return recordID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getMedications() {
        return medications;
    }

    public String getTreatments() {
        return treatments;
    }

    // Setters
    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }
}