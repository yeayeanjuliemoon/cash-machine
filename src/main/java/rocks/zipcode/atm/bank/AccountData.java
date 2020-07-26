package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;
    private final String type;
    private final Float balance;

    public String getType() {
        return type;
    }

    AccountData(int id, String name, String email, Float balance, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Float getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                "Balance: " + balance + '\n' +
                "Type: " + type;
    }

}
