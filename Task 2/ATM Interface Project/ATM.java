import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Account currentAccount;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        scanner = new Scanner(System.in);
    }

    private void welcomeScreen() {

        System.out.println("\n");
        System.out.println("================================================");
        System.out.println("                                                ");
        System.out.println("              WELCOME TO                       ");
        System.out.println("                                                ");
        System.out.println("          ★ SMART ATM BANKING ★                ");
        System.out.println("                                                ");
        System.out.println("         FAST  |. RELEVANT  | SECURE  |  EASY               ");
        System.out.println("                                                ");
        System.out.println("================================================");
        System.out.println("           YOUR MONEY, YOUR CONTROL             ");
        System.out.println("================================================");
    }

    private boolean login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("\nEnter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter 4-Digit PIN: ");
            String pin = scanner.nextLine();

            if (bank.authenticate(userId, pin)) {

                currentAccount = bank.getAccount(userId);

                System.out.println("\n================================");
                System.out.println("       LOGIN SUCCESSFUL!");
                System.out.println("================================");
                System.out.println("Welcome, "
                        + currentAccount.getHolderName());

                return true;
            }

            attempts++;

            System.out.println("\nInvalid User ID or PIN!");

            System.out.println("Attempts remaining: "
                    + (3 - attempts));
        }

        System.out.println("\nACCESS DENIED!");
        System.out.println("Too many incorrect login attempts.");

        return false;
    }

    private void showMenu() {

        System.out.println("\n");
        System.out.println("========================================");
        System.out.println("             ATM MAIN MENU              ");
        System.out.println("========================================");
        System.out.println("  1. Transaction History");
        System.out.println("  2. Withdraw");
        System.out.println("  3. Deposit");
        System.out.println("  4. Transfer");
        System.out.println("  5. Check Balance");
        System.out.println("  6. Quit");
        System.out.println("========================================");

        System.out.print("Choose an option: ");
    }

    private void transactionHistory() {

        System.out.println("\n========================================");
        System.out.println("          TRANSACTION HISTORY           ");
        System.out.println("========================================");

        if (currentAccount.getTransactions().isEmpty()) {
            System.out.println("No transactions found.");
        } else {

            for (Transaction transaction :
                    currentAccount.getTransactions()) {

                transaction.display();
            }
        }

        System.out.println("========================================");
    }

    private void withdraw() {

        System.out.print("\nEnter withdrawal amount: Rs. ");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (currentAccount.withdraw(amount)) {

            System.out.println("\nWithdrawal Successful!");
            System.out.printf("Withdrawn: Rs. %.2f%n", amount);

            System.out.printf("Remaining Balance: Rs. %.2f%n",
                    currentAccount.getBalance());

        } else {

            System.out.println("\nInsufficient Funds!");
        }
    }

    private void deposit() {

        System.out.print("\nEnter deposit amount: Rs. ");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        currentAccount.deposit(amount);

        System.out.println("\nDeposit Successful!");

        System.out.printf("Deposited: Rs. %.2f%n", amount);

        System.out.printf("Available Balance: Rs. %.2f%n",
                currentAccount.getBalance());
    }

    private void transfer() {

        System.out.print("\nEnter recipient Account ID: ");
        String receiverId = scanner.nextLine();

        Account receiver = bank.getAccount(receiverId);

        if (receiver == null) {
            System.out.println("Recipient account not found!");
            return;
        }

        if (receiverId.equals(currentAccount.getAccountId())) {
            System.out.println("Cannot transfer to your own account!");
            return;
        }

        System.out.print("Enter transfer amount: Rs. ");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (currentAccount.transfer(receiver, amount)) {

            System.out.println("\nTransfer Successful!");

            System.out.println("Transferred To: "
                    + receiver.getHolderName());

            System.out.printf("Amount: Rs. %.2f%n", amount);

            System.out.printf("Remaining Balance: Rs. %.2f%n",
                    currentAccount.getBalance());

        } else {

            System.out.println("\nInsufficient Funds!");
        }
    }

    private void checkBalance() {

        System.out.println("\n================================");
        System.out.println("          ACCOUNT BALANCE       ");
        System.out.println("================================");

        System.out.printf("Available Balance: Rs. %.2f%n",
                currentAccount.getBalance());

        System.out.println("================================");
    }

    private double readAmount() {

        try {
            double amount = Double.parseDouble(scanner.nextLine());

            if (Double.isNaN(amount) || Double.isInfinite(amount)) {
                return -1;
            }

            return amount;

        } catch (NumberFormatException e) {

            return -1;
        }
    }


    public void start() {

        welcomeScreen();

        if (!login()) {
            return;
        }

        boolean running = true;

        while (running) {

            showMenu();

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    transactionHistory();
                    break;

                case "2":
                    withdraw();
                    break;

                case "3":
                    deposit();
                    break;

                case "4":
                    transfer();
                    break;

                case "5":
                    checkBalance();
                    break;

                case "6":

                    System.out.println("\n================================");
                    System.out.println("       THANK YOU FOR BANKING    ");
                    System.out.println("================================");
                    System.out.println("       HAVE A NICE DAY!");
                    System.out.println("================================");

                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid option! Try again.");
            }
        }
    }
}