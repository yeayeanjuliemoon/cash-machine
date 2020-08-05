import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.bank.AccountData;

public class AccountDataTest {

    @Test
    public void testNonNullaryConstructor() {
        int expectedId = 1000;
        String expectedName = "Example 1";
        String expectedEmail = "example1@gmail.com";
        String expectedType = "Basic";
        Float expectedBalance = (float) 500;

        AccountData accountData = new AccountData(expectedId, expectedName, expectedEmail, expectedBalance, expectedType);

        Assert.assertEquals(expectedId, accountData.getId());
        Assert.assertEquals(expectedName, accountData.getName());
        Assert.assertEquals(expectedEmail, accountData.getEmail());
        Assert.assertEquals(expectedType, accountData.getType());
        Assert.assertEquals(expectedBalance, accountData.getBalance());

    }

    @Test
    public void testToString() {
        AccountData accountData = new AccountData(1000, "Example 1", "example1@gmail.com", (float) 500, "Basic");

        String expected = "Account id: " + 1000 + '\n' +
                "Name: " + "Example 1" + '\n' +
                "Email: " + "example1@gmail.com" + '\n' +
                "Balance: " + (float) 500 + '\n' +
                "Type: " + "Basic";

        String actual = accountData.toString();

        Assert.assertEquals(expected, actual);
    }

}
