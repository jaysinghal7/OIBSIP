import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private double amount;
    private double balance;
    private String description;
    private String dateTime;

    public Transaction(String type, double amount,
                       double balance, String description) {

        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.description = description;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        dateTime = LocalDateTime.now().format(formatter);
    }

    public void display() {

        System.out.println("------------------------------------------");
        System.out.println("Date        : " + dateTime);
        System.out.println("Transaction : " + type);
        System.out.printf("Amount      : Rs. %.2f%n", amount);
        System.out.printf("Balance     : Rs. %.2f%n", balance);
        System.out.println("Description : " + description);
    }
}