import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String owner;
    private int balance;
    private List<String> transactionHistory = new ArrayList<>();

    public Account(String accountNumber, String owner, int balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        transactionHistory.add("Account created with initial deposit: " + balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
        transactionHistory.add("Deposit: " + amount);
    }

    public int withdraw(int amount) throws Exception {
        if (this.balance < amount) {
            throw new Exception("Insufficient funds");
        } else {
            this.balance -= amount;
            transactionHistory.add("Withdrawal: " + amount);
            return amount;
        }
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
