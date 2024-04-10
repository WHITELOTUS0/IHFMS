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
        Connection conn = null;
        PreparedStatement pstmtInvoice = null;
        ResultSet generatedKeys = null;
        try {
            conn = dbConnection.getConnection();
            pstmtInvoice = conn.prepareStatement(sqlInvoice, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmtInvoice.setInt(1, invoice.getPatientID());
            pstmtInvoice.setDouble(2, invoice.getAmount());
            int affectedRows = pstmtInvoice.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = pstmtInvoice.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int invoiceId = generatedKeys.getInt(1);
                    invoice.setInvoiceID(invoiceId);
                    addInvoiceServices(invoice.getServicesProvided(), invoiceId, conn);
                }
            }
        } finally {
            if (generatedKeys != null) generatedKeys.close();
            if (pstmtInvoice != null) pstmtInvoice.close();
            if (conn != null) conn.close();
        }
    }

    private void addInvoiceServices(List<Service> services, int invoiceId, Connection conn) throws SQLException {
        String sqlService = "INSERT INTO services (description, cost) VALUES (?, ?)";
        String sqlInvoiceService = "INSERT INTO invoice_services (invoice_id, service_id) VALUES (?, ?)";
        PreparedStatement pstmtService = null;
        PreparedStatement pstmtInvoiceService = null;
        ResultSet generatedServiceKeys = null;
        try {
            for (Service service : services) {
                // First, add the service to the services table
                pstmtService = conn.prepareStatement(sqlService, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmtService.setString(1, service.getDescription());
                pstmtService.setDouble(2, service.getCost());
                int serviceAffectedRows = pstmtService.executeUpdate();

                if (serviceAffectedRows > 0) {
                    // Get the generated service ID
                    generatedServiceKeys = pstmtService.getGeneratedKeys();
                    if (generatedServiceKeys.next()) {
                        int serviceId = generatedServiceKeys.getInt(1);

                        // Then, add the service to the invoice_services table with the correct service ID
                        pstmtInvoiceService = conn.prepareStatement(sqlInvoiceService);
                        pstmtInvoiceService.setInt(1, invoiceId);
                        pstmtInvoiceService.setInt(2, serviceId);
                        pstmtInvoiceService.executeUpdate();
                    }
                }
                // Close the Result Set and Prepared Statement for each service to avoid resource leak
                if (generatedServiceKeys != null) {
                    generatedServiceKeys.close();
                    generatedServiceKeys = null;
                }
                if (pstmtService != null) {
                    pstmtService.close();
                    pstmtService = null;
                }
            }
        } finally {
            // Close Prepared Statements if they are still open
            if (generatedServiceKeys != null) generatedServiceKeys.close();
            if (pstmtService != null) pstmtService.close();
            if (pstmtInvoiceService != null) pstmtInvoiceService.close();
        }
    }
}