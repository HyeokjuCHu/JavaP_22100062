
import java.util.ArrayList;
import java.util.List;

public class BasicAccount implements Account {
    private String accountNumber;
    private String owner;
    private int balance;
    private List<String> transactionHistory = new ArrayList<>();

    public BasicAccount(String accountNumber, String owner, int balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        transactionHistory.add("Account created with initial deposit: " + balance);
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void deposit(int amount) {
        this.balance += amount;
        transactionHistory.add("Deposit: " + amount);
    }

    @Override
    public int withdraw(int amount) throws Exception {
        if (this.balance < amount) {
            throw new Exception("Insufficient funds");
        } else {
            this.balance -= amount;
            transactionHistory.add("Withdrawal: " + amount);
            return amount;
        }
    }

    @Override
    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

