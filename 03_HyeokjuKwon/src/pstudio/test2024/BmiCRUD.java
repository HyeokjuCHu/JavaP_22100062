package pstudio.test2024;

import java.io.*;
import java.util.*;

public class BmiCRUD implements iCRUD{
    private ArrayList<BmiItem> list;
    public BmiCRUD() {
        this.list = new ArrayList<BmiItem>();
    }

    public void loadData() throws IOException {
        File file = new File("src/data.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;

        while((line = br.readLine()) != null) {
            String[] lines = line.split("/");
            BmiItem item = new BmiItem(lines[0], Integer.valueOf(lines[1]), Integer.valueOf(lines[2]));
            list.add(item);
        }
        br.close();
    }

    public void saveData() throws IOException {
        File file = new File("src/data.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        for (BmiItem item : list) {
            pw.println(item.getName() + "/" + item.getHeight() + "/" + item.getWeight());
        }
        pw.close();
    }

    public void Filewrite() throws IOException {
            String filePath = "src/report.txt";
            File file = new File(filePath); // File객체 생성
            if(!file.exists()){ // 파일이 존재하지 않으면
                file.createNewFile(); // 신규생성

            // BufferedWriter 생성
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));

            // 파일에 쓰기
            System.out.println("Total " + this.list.size() + " records");
            for (BmiItem item : this.list) {
                writer.write(item.toString());
                writer.printf("\n");
            }

            int c1=0,c2=0,c3=0,c4=0;
            double s1=0,s2=0,s3=0,s4=0;
            for (BmiItem item : this.list) {
                if (item.getLevel().equalsIgnoreCase("Underweight")) {
                    c1++;
                    s1+=item.getBmi();
                }else if (item.getLevel().equalsIgnoreCase("Healthy Weight")) {
                    c2++;
                    s2+=item.getBmi();
                }else if (item.getLevel().equalsIgnoreCase("Overweight")) {
                    c3++;
                    s3+=item.getBmi();
                }else if (item.getLevel().equalsIgnoreCase("Obesity")) {
                    c4++;
                    s4+=item.getBmi();
                }

            }

                writer.printf("1) Underweight - %d (average %.1f)\n",c1,s1/(double)c1);
                writer.printf("2) Healthy Weight - %d (average %.1f)\n",c2,s2/(double)c2);
                writer.printf("3) Overweight - %d (average %.1f)\n",c3,s3/(double)c3);
                writer.printf("4) Obesity - %d (average %.1f)\n",c4,s4/(double)c4);
            // 버퍼 및 스트림 뒷정리
            writer.flush(); // 버퍼의 남은 데이터를 모두 쓰기
            writer.close(); // 스트림 종료
        }
    }



    @Override
    public Object createItem() {
        return null;
    }

    @Override
    public int addItem() {
        String name;
        int height, weight;
        Scanner sc = new Scanner(System.in);

        System.out.print("Add a new record\n"
                + "Enter your name: ");
        name = sc.nextLine().trim();;

        System.out.print("Enter height and weight: ");
        height = sc.nextInt();;
        weight = sc.nextInt();;

        BmiItem newitem = new BmiItem(name, height, weight);
        this.list.add(newitem);
        System.out.println("Record added.");
        return 0;
    }

    @Override
    public int updateItem() {
        int height, weight;
        Scanner sc = new Scanner(System.in);

        System.out.print("Edit a record\n"
                + "Enter the name to edit: ");
        String name = sc.nextLine().trim();
        BmiItem item_found = findName(name);
        if(item_found == null) {
            System.out.println("Not found.");
            return 1;
        }

        System.out.println(item_found.toString());

        System.out.print("Enter height and weight: ");
        height = sc.nextInt();
        weight = sc.nextInt();
        item_found.setHeight(height);
        item_found.setWeight(weight);
        item_found.resetLevel();
        item_found.setReg_date(new Date());

        System.out.println(item_found.toString());
        System.out.println("Record updated.");
        return 0;
    }

    @Override
    public int deleteItem() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Delete a record\n"
                + "Enter the name to remove: ");
        String name = sc.nextLine().trim();

        BmiItem item_found = findName(name);
        if(item_found == null) {
            System.out.println("Not found.");
            return 1;
        }

        System.out.println(item_found.toString());

        this.list.remove(item_found);
        System.out.println("Record deleted.");
        return 0;
    }

    public BmiItem findName(String name){
        for (BmiItem item : this.list) {
            if (item.getName().equals(name))
                return item;
        }
        return null;

    }

    @Override
    public int printItem() {
        return 0;
    }

    public int printAll() {
        System.out.print("sort by [1]height, [2]weight > ");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        if(x==1){
            Collections.sort(this.list, new HeightComparator());
        }else if(x==2){
            Collections.sort(this.list, new WeightComparator());
        }
        System.out.println("Total " + this.list.size() + " records");
        for (BmiItem item : this.list) {
            System.out.println(item.toString());
        }

        return 0;
    }
    public void sortByBmi() {
        Collections.sort(this.list, new BmiItemBmiComparator());
    }

    public void sortByName() {
        Collections.sort(this.list, new BmiItemNameComparator());
    }

    public void reverseList() {
        Collections.reverse(this.list);
    }


    public int searchByLevel() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter BMI level (Underweight, Healthy Weight, Overweight, Obesity): ");
        String level = sc.nextLine().trim();

        boolean found = false;
        for (BmiItem item : this.list) {
            if (item.getLevel().equalsIgnoreCase(level)) {
                System.out.println(item.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No records found for the given BMI level.");
        }
        return 0;
    }

public int findWithinBMIRange() {
        int count=0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter minimum BMI: ");
        double minBmi = sc.nextDouble();
        System.out.print("Enter maximum BMI: ");
        double maxBmi = sc.nextDouble();

        boolean found = false;
        for (BmiItem item : this.list) {
            if (item.getBmi() >= minBmi && item.getBmi() <= maxBmi) {
                System.out.println(item.toString());
                found = true;
                count++;
            }
        }

        if (!found) {
            System.out.println("No records found within the given BMI range.");
        }
        System.out.printf("%d records found.",count);
        return 0;
    }
}


class BmiItemBmiComparator implements Comparator<BmiItem> {
    @Override
    public int compare(BmiItem o1, BmiItem o2) {
        if (o1.getBmi() < o2.getBmi())
            return -1;
        else
            return 1;
    }
}
class BmiItemNameComparator implements Comparator<BmiItem> {
    @Override
    public int compare(BmiItem o1, BmiItem o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class HeightComparator implements Comparator<BmiItem> {
    @Override
    public int compare(BmiItem o1, BmiItem o2) {
        if (o1.getHeight() < o2.getHeight())
            return -1;
        else
            return 1;
    }
}

class WeightComparator implements Comparator<BmiItem> {
    @Override
    public int compare(BmiItem o1, BmiItem o2) {
        if (o1.getWeight() < o2.getWeight())
            return -1;
        else
            return 1;
    }
}
