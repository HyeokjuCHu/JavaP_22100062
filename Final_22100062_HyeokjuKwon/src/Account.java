
import java.util.List;

public interface Account {
    String getAccountNumber();
    String getOwner();
    int getBalance();
    void deposit(int amount);
    int withdraw(int amount) throws Exception;
    List<String> getTransactionHistory();
}
