import org.junit.Assert;
import org.junit.jupiter.api.Test;
import rocks.zipcode.atm.CashMachine;
import rocks.zipcode.atm.bank.Bank;

public class CashMachineTest {

    @Test
    public void testGetAccountData() {
        Bank bank = new Bank();
        CashMachine cashMachine = new CashMachine(bank);

        Assert.assertNull(cashMachine.getAccountData());
    }

    @Test
    public void testToString() {
        Bank bank = new Bank();
        CashMachine cashMachine = new CashMachine(bank);

        String expected = "Enter an Account ID or Email and Click Submit";
        String actual = cashMachine.toString();

        Assert.assertEquals(expected, actual);


    }
}
