public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Create demo accounts
        bank.addAccount(new Account("1001", "1234", "Jay Singh", 25000));
        bank.addAccount(new Account("1002", "5678", "Rahul Sharma", 15000));
        bank.addAccount(new Account("1003", "1111", "Aman Verma", 30000));

        ATM atm = new ATM(bank);
        atm.start();
    }
}
