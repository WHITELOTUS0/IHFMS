package ihfms.dao;

import ihfms.model.Patient;
import ihfms.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private DatabaseConnection dbConnection;

    public PatientDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients (first_name, last_name, date_of_birth, gender, contact_info, phone_number, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, patient.getFirstName());
            pstmt.setString(2, patient.getLastName());
            pstmt.setDate(3, new java.sql.Date(patient.getDateOfBirth().getTime()));
            pstmt.setString(4, patient.getGender());
            pstmt.setString(5, patient.getContactInfo());
            pstmt.setString(6, patient.getPhoneNumber());
            pstmt.setString(7, patient.getEmail());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        patient.setPatientID(rs.getInt(1));
                    }
                }
            }
        }
    }

    public List<Patient> fetchAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientID(rs.getInt("patient_id"));
                patient.setFirstName(rs.getString("first_name"));
                patient.setLastName(rs.getString("last_name"));
                patient.setEmail(rs.getString("email"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                // Set other properties from the result set
                patients.add(patient);
            }
        }
        System.out.println("Fetched " + patients.size() + " patients from the database.");
        return patients;
    }
}