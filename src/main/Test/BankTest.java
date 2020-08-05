import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.zipcode.atm.ActionResult;
import org.junit.Assert;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;


class BankTest {

    private  int id;
    private  String name;
    private  String email;
    private  String type;
    private  Float balance;
    AccountData accountData;
    Bank bank;


    @BeforeEach
    public void setUp(){
        id = 1000;
        name = "Example1";
        email = "example1@gmail.com";
        type = "Premium";
        balance = Float.valueOf(2000);
        accountData = new AccountData(id, name, email,balance,type);
        bank = new Bank();

    }

    @Test
    void withdrawTest() {
        ActionResult<AccountData> withdrawnAmount = bank.withdraw(accountData, 200.0f);
        Assert.assertNotNull(withdrawnAmount);
        Assert.assertTrue(withdrawnAmount.getData().getBalance().equals(300.0f));
    }


    @Test
    void getAccountByTypeTest() {
        ActionResult<AccountData> account = bank.getAccountByType("Example 1","Premium");
        Assert.assertTrue(account.getData().getType().equals("Premium"));
    }

    @Test
    void getAccountByTypeTestKsa() {
        ActionResult<AccountData> account = bank.getAccountByType("Example 2","Kids Savings");
        Assert.assertTrue(account.getData().getType().equals("Kids Savings"));
    }
}