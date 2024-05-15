package midexam;

import java.util.Scanner;

public class J_NO_Two {
    public static void main(String[] args) {
        midexam.J_NO_Two pStudio = new midexam.J_NO_Two();
        pStudio.J2();
    }
    void J2(){
        Scanner in = new Scanner(System.in);//scanner in
        int x;
        x=in.nextInt();
        int sum=0;


        System.out.printf("500 * %d\n",x/500);
        sum+=x/500;
        x%=500;
        System.out.printf("100 * %d\n",x/100);
        sum+=x/100;
        x%=100;
        System.out.printf("50 * %d\n",x/50);
        sum+=x/50;
        x%=50;
        System.out.printf("10 * %d\n",x/10);
        sum+=x/10;
        x%=10;
        System.out.printf("1 * %d\n",x);
        sum+=x;
        System.out.printf("Total: %d",sum);






    }
}
