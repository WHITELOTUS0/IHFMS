package ihfms.commands;

import ihfms.model.Account;

public class ReconciliationCommand implements Command {
    private Account account;

    public ReconciliationCommand(Account account) {
        this.account = account;
    }

    @Override
    public void execute() {
        // Logic to reconcile the account
    }
}