package midexam;

import java.util.Scanner;


public class J_NO_One {
    public static void main(String[] args) {
        J_NO_One pStudio = new J_NO_One();
        pStudio.J1();

    }

    void J1(){
        Scanner in = new Scanner(System.in);//scanner in


        int x;
        x=in.nextInt();

        for(int i=1;i<=6;i++){
            for(int j=1;j<=6;j++){
                for(int z=1;z<=6;z++){
                    if(i+j+z==x){
                        System.out.printf("%d-%d-%d\n",i,j,z);
                    }
                }
            }
        }

    }

    void J2(){

    }


}




