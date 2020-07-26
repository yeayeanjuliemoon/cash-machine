package rocks.zipcode.atm.bank;

import javafx.scene.control.Alert;
import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(1000, "Example 1", "example1@gmail.com", 500)));
        accounts.put(1001, new BasicAccount(new AccountData(1001, "Example 2", "example2@gmail.com", 1000)));
        accounts.put(1002, new BasicAccount(new AccountData(1002, "Example 3", "example3@gmail.com", 3000)));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 4", "example4@gmail.com", 2000
        )));
        accounts.put(2001, new PremiumAccount(new AccountData(
                2001, "Example 5", "example5@gmail.com", 9000
        )));
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry a different account number");
        }
    }

    public ActionResult<AccountData>  getAccountByEmail(String email) {
        Iterator it = accounts.entrySet().iterator();
        while (it.hasNext()) {
            Entry pair = (Entry)it.next();
            Account bankValue = (Account) pair.getValue();
            if (bankValue.getAccountData().getEmail().equals(email)) {
                return ActionResult.success(bankValue.getAccountData());
            }
        }
        return ActionResult.fail("No account with email: " + email + "\nTry a different email address");
    }


    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);
        if (ok) {

                return ActionResult.success(account.getAccountData());
        }
         else {
            Alert overDraftAlert = new Alert(Alert.AlertType.WARNING);
            overDraftAlert.setContentText("This transaction is above the OverDraft Limit");
            overDraftAlert.showAndWait();
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());

        }
    }

}

