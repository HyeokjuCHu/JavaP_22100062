public class Account {
    private String ano; // Account number
    private String owner; // Account owner
    private int balance; // Account balance

    public Account(String ano, String owner, int balance) {
        this.ano = ano;
        this.owner = owner;
        this.balance = balance;
    }

    public String getAno() {
        return ano;
    }

    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public int withdraw(int amount) throws Exception {
        if (this.balance < amount) {
            throw new Exception("Not Enough Money");
        } else {
            this.balance -= amount;
            return amount;
        }
    }
}
