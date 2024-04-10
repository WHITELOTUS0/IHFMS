package ihfms.model;

import ihfms.dao.ServiceDAO;

public class Service {
    private String serviceId;
    private String description;
    private double cost;

    public Service(String serviceId, String description, double cost) {
        this.serviceId = serviceId;
        this.description = description;
        this.cost = cost;
    }

    // Getters and setters
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void saveToDatabase(ServiceDAO serviceDAO) {
        serviceDAO.saveService(this);
    }
}
