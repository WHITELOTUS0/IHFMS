package ihfms.model;

public class Account {
    private String accountID;
    private String accountType;
    private double balance;

    // Getters
    public String getAccountID() {
        return accountID;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    // Setters
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}