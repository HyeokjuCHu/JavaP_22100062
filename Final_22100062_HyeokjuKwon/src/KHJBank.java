import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class KHJBank {
    private static Account[] accountArray = new Account[100];
    private static Scanner sc = new Scanner(System.in);
    private static final String PREFIX = "Bank-";
    private static int seq = 0; // 계좌번호 자동발번
    private static boolean isCreated = false; // 계좌등록여부

    public static void main(String[] args) {
        boolean runProgram = true;

        while (runProgram) {
            boolean loggedIn = false;
            while (!loggedIn) {
                System.out.println("-------------------------------------");
                System.out.println("1. 회원가입 | 2. 로그인 | 3. 종료");
                System.out.println("-------------------------------------");
                System.out.print("선택>");
                int selectNo = sc.nextInt();
                switch (selectNo) {
                    case 1:
                        Login.register();
                        break;
                    case 2:
                        loggedIn = Login.login();
                        break;
                    case 3:
                        System.out.println("프로그램 종료");
                        return;
                }
            }

            boolean run = true;
            while (run) {
                System.out.println("-------------------------------------");
                System.out.println("1.계좌생성 - 2.계좌목록 - 3.예금 - 4.출금 - 5.종료 - 6.로그아웃");
                System.out.println("-------------------------------------");
                System.out.print("선택>");
                int selectNo = sc.nextInt();
                switch (selectNo) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        accountList();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        withdraw();
                        break;
                    case 5:
                        run = false;
                        runProgram = false;
                        break;
                    case 6:
                        run = false; // End the current session and go back to login menu
                        break;
                }
            }
        }
        System.out.println("프로그램 종료");
    }

    private static void withdraw() {
        if (!isRegistered()) {
            System.out.println("먼저 계좌등록을 하세요");
            return; // 메소드 실행 종료.
        }
        accountList();
        System.out.println("출금할 계좌번호를 선택하세요>");
        Account account;
        while (true) {
            String ano = sc.next(); // 출금 계좌번호 입력
            account = findAccount(ano); // 입력받은 정보로 계좌조회
            if (account == null)
                System.out.println("계좌번호를 확인하세요");
            else
                break; // 반복문 빠져나가기
        }
        System.out.print("출금할 금액을 입력하세요>");
        int amount = sc.nextInt();
        int result;
        try {
            result = account.withdraw(amount); // 잔액 > 출금액보다 큰 경우
            System.out.println("출금액:" + result);
        } catch (Exception e) { // 잔액 < 출금액보다 작은경우
            System.out.println(e.getMessage());
        }
    }

    private static void deposit() {
        if (!isRegistered()) {
            System.out.println("먼저 계좌등록을 하세요");
            return;
        }
        accountList();
        System.out.println("입금할 계좌번호를 선택하세요>");
        Account account;
        while (true) {
            String ano = sc.next();
            account = findAccount(ano);
            if (account == null)
                System.out.println("계좌번호를 확인하세요>");
            else
                break;
        }
        System.out.print("입금할 금액을 입력하세요>");
        int amount = sc.nextInt();
        account.deposit(amount); // 입금 처리
        System.out.println("예금 성공");
    }

    private static void accountList() {
        if (!isRegistered()) {
            System.out.println("먼저 계좌등록을 하세요");
            return;
        }
        for (Account account : accountArray) {
            if (account != null) {
                System.out.println(account.getAno() + " " + account.getOwner() + " " + account.getBalance());
            }
        }
    }

    private static void createAccount() {
        String ano = PREFIX + String.format(new DecimalFormat("0000").format(++seq));
        sc.nextLine(); // Clear the buffer
        System.out.print("소유주명>");
        String owner = sc.nextLine(); // Use nextLine() to read the entire line
        int amount = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("초기입금액>");
                amount = sc.nextInt();
                validInput = true; // If we get here, the input was valid
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
                sc.next(); // Clear the invalid input from the scanner
            }
        }

        for (int i = 0; i < accountArray.length; i++) {
            if (accountArray[i] == null) {
                accountArray[i] = new Account(ano, owner, amount);
                System.out.println("계좌 등록 성공");
                isCreated = true;
                break;
            }
        }
    }

    private static boolean isRegistered() {
        return isCreated;
    }

    private static Account findAccount(String ano) {
        Account account = null;
        for (Account acc : accountArray) {
            if (acc != null && acc.getAno().equals(ano)) {
                account = acc;
            }
        }
        return account;
    }
}
