import java.util.HashMap;

public class Bank {

    private HashMap<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public boolean authenticate(String accountId, String pin) {

        Account account = getAccount(accountId);

        return account != null && account.verifyPin(pin);
    }
}