package ihfms.dao;

import ihfms.model.Invoice;
import ihfms.model.Service;
import ihfms.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InvoiceDAO {
    private DatabaseConnection dbConnection;

    public InvoiceDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addInvoice(Invoice invoice) throws SQLException {
        String sqlInvoice = "INSERT INTO invoices (patient_id, total_cost) VALUES (?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmtInvoice = conn.prepareStatement(sqlInvoice, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmtInvoice.setInt(1, invoice.getPatientID());
            pstmtInvoice.setDouble(2, invoice.getAmount());
            int affectedRows = pstmtInvoice.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmtInvoice.getGeneratedKeys()) {
                    if (rs.next()) {
                        int invoiceId = rs.getInt(1);
                        invoice.setInvoiceID(invoiceId);
                        addInvoiceServices(invoice.getServicesProvided(), invoiceId, conn);
                    }
                }
            }
        }
    }

    private void addInvoiceServices(List<Service> services, int invoiceId, Connection conn) throws SQLException {
        String sqlService = "INSERT INTO invoice_services (invoice_id, service_id, description, cost) VALUES (?, ?, ?, ?)";
        for (Service service : services) {
            try (PreparedStatement pstmtService = conn.prepareStatement(sqlService)) {
                pstmtService.setInt(1, invoiceId);
                pstmtService.setString(2, service.getServiceId());
                pstmtService.setString(3, service.getDescription());
                pstmtService.setDouble(4, service.getCost());
                pstmtService.executeUpdate();
            }
        }
    }
}