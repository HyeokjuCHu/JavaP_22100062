public class Account {
    //필드
    private String ano;//계좌번호
    private String owner;//소유자
    private int balance;//잔고
    //생성자
    public Account(String ano, String owner, int balance) {
        //super();//=> Account extends Object
        this.ano = ano;
        this.owner = owner;
        this.balance = balance;
    }
    //메소드
    protected String getAno() { return ano; }
    protected void setAno(String ano) { this.ano = ano; }
    protected String getOwner() {       return owner;   }
    protected void setOwner(String owner) { this.owner = owner; }
    protected int getBalance() {    return balance; }
    protected void setBalance(int balance) {this.balance = balance; }
    public void deposit(int amount) {
        this.balance+=amount;
    }
    public int withdraw(int amount) throws Exception {//예외 처리를 호출한 곳으로 떠넘김
        if(this.balance<amount) { //강제 예외 처리
            throw new Exception("잔액이 모자랍니다.");
        }else { //this.balance>=amount
            this.balance-=amount;

            return amount;
        }
    }
}
