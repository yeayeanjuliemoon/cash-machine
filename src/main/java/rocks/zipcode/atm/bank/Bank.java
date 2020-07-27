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
        accounts.put(1000, new BasicAccount(new AccountData(1000, "Example 1", "example1@gmail.com", (float) 500,"Basic")));
        accounts.put(1001, new BasicAccount(new AccountData(1001, "Example 2", "example2@gmail.com", (float) 1000,"Basic")));
        accounts.put(1002, new BasicAccount(new AccountData(1002, "Example 3", "example3@gmail.com", (float) 3000,"Basic")));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 1", "example1@gmail.com", (float) 2000,"Premium"
        )));
        accounts.put(2001, new PremiumAccount(new AccountData(
                2001, "Example 5", "example5@gmail.com", (float) 9000,"Premium"
        )));

        accounts.put(3000, new KidsAccount(new AccountData(
                3000, "Example 2", "example2@gmail.com", (float) 2000,"Kids Savings"
        )));
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            Alert noId = new Alert(Alert.AlertType.WARNING);
            noId.setContentText("Please enter a different Account ID");
            noId.showAndWait();

            return ActionResult.fail("No account with id: " + id + "\nTry a different account number");
        }
    }

    public ActionResult<AccountData>  getAccountByEmail(String email) {
        Iterator it = accounts.entrySet().iterator();
        while (it.hasNext()) {
            Entry pair = (Entry) it.next();
            Account bankValue = (Account) pair.getValue();
            if (bankValue.getAccountData().getEmail().equals(email)) {
                return ActionResult.success(bankValue.getAccountData());
            }
        }
                Alert noEmail = new Alert(Alert.AlertType.WARNING);
                noEmail.setContentText("Please enter a different email");
                noEmail.showAndWait();

        return ActionResult.fail("No account with email: " + email + "\nTry a different email address");

    }


    public ActionResult<AccountData> deposit(AccountData accountData, Float amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, Float amount) {
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

    public ActionResult<AccountData>  getAccountByType(String name, String type) {
        Iterator it = accounts.entrySet().iterator();
        while (it.hasNext()) {
            Entry account = (Entry)it.next();
            Account bankValue = (Account) account.getValue();
            if (bankValue.getAccountData().getName().equals(name) && bankValue.getAccountData().getType().equals(type)) {
                return ActionResult.success(bankValue.getAccountData());
            }
        }
        Alert noAccount = new Alert(Alert.AlertType.ERROR);
        noAccount.setContentText("No Account Found");
        noAccount.showAndWait();
        return ActionResult.fail("No account found");
    }
}

