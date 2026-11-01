import java.util.ArrayList;

public class Account {

    private String accountId;
    private String pin;
    private String holderName;
    private double balance;

    private ArrayList<Transaction> transactions;

    public Account(String accountId, String pin,
                   String holderName, double balance) {

        this.accountId = accountId;
        this.pin = pin;
        this.holderName = holderName;
        this.balance = balance;

        transactions = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public boolean hasSufficientBalance(double amount) {
        return balance >= amount;
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("Deposit", amount,
                "Cash deposited");
    }

    public boolean withdraw(double amount) {

        if (hasSufficientBalance(amount)) {
            balance -= amount;

            addTransaction("Withdrawal", amount,
                    "Cash withdrawn");

            return true;
        }

        return false;
    }

    public boolean transfer(Account receiver, double amount) {

        if (hasSufficientBalance(amount)) {

            balance -= amount;
            receiver.balance += amount;

            addTransaction("Transfer", amount,
                    "Sent to Account " + receiver.accountId);

            receiver.addTransaction("Received", amount,
                    "Received from Account " + accountId);

            return true;
        }

        return false;
    }

    public void addTransaction(String type,
                               double amount,
                               String description) {

        transactions.add(
            new Transaction(type, amount, balance, description)
        );
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}