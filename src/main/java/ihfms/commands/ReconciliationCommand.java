package ihfms.commands;

import ihfms.model.Account;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReconciliationCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ReconciliationCommand.class.getName());
    private Account account;

    public ReconciliationCommand(Account account) {
        this.account = account;
    }

    @Override
    public void execute() {
        try {
            // Logic to reconcile the account
            LOGGER.log(Level.INFO, "Account reconciliation command executed for account ID: {0}", account.getAccountID());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error executing account reconciliation command for account ID: {0}", account.getAccountID());
            // Handle the error appropriately
        }
    }
}
//package ihfms.commands;
//
//import ihfms.model.Account;
//
//public class ReconciliationCommand implements Command {
//    private Account account;
//
//    public ReconciliationCommand(Account account) {
//        this.account = account;
//    }
//
//    @Override
//    public void execute() {
//        // Logic to reconcile the account
//    }
//}