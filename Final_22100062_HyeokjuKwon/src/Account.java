public class Account {
    //필드
    private String ano;//계좌번호
    private String owner;//소유주
    private int balance;//잔고
    //생성자
    public Account(String ano, String owner, int balance) {
        //super();//=> Account extends Object
        this.ano = ano;
        this.owner = owner;
        this.balance = balance;
    }
    //메소드
    protected String getAno() {
        return ano;
    }

    protected void setAno(String ano) {
        this.ano = ano;
    }

    protected String getOwner() {
        return owner;

    }
    protected void setOwner(String owner) {
        this.owner = owner;

    }

    protected int getBalance() {
        return balance;
    }

    protected void setBalance(int balance) {
        this.balance = balance;

    }

    public void deposit(int amount) {
        this.balance+=amount;
    }

    public int withdraw(int amount) throws Exception {
        if(this.balance<amount) {
            throw new Exception("No Enough Money");
        }else { //this.balance>=amount
            this.balance-=amount;

            return amount;
        }
    }
}
