class Test {

    public static void main(String[] args) {

        String s = "12,345,678";

        // ①
        int n = Integer.parseInt(s.replace(",", ""));

        int m = 123;

        // ③
        String s2 = String.valueOf(m);

        char c = '김';

        m = (int)c;

        // ④
        String s3 = String.format("%d %x %c", m, (int)c, c);

        double d = Math.sqrt(2); // ⑥

        double d2 = Math.PI; // ⑦

    }

}

/*
*
class Student {

    private String name;  // 성명 = 성씨,이름
    private int num;       // 학번

    // 생성자 메소드
    public Student(String name, int num) {
        this.name = name;
        this.num = num;
    }

    // 성명 반환
    public String getName() {
        return this.name;
    }

    // 성씨 반환
    public String getFamilyName() {
        return this.name.split(",")[0];
    }

    // 이름 반환
    public String getGivenName() {
        return this.name.split(",")[1];
    }

    // 성씨 추출
    public static String getFamilyName(String name) {
        return name.split(",")[0];
    }

    // 학번 반환
    public int getNumber() {
        return this.num;
    }

    // 입학년도 문자열 반환
    public String getYearString() {
        return Integer.toString(this.num).substring(0, 4);
    }

    // 오버로드된 생성자 메소드 추가
    public Student(String familyName, String givenName, int admissionYear, int sequenceNumber) {
        this.name = familyName + "," + givenName;
        this.num = admissionYear * 10000 + sequenceNumber;
    }
}

class Test {

    public static void main(String[] args) {

        Student s1 = new Student("홍,길동", 20170001);
        Student s2 = new Student("선우,경성", 20180010);

        // 오류: Student 클래스의 name 필드가 private이므로 직접 접근할 수 없음
        // System.out.println(s1.name);

        // 올바른 방법: getName() 메소드를 통해 성명 출력
        System.out.println(s1.getName());

        // 학번 출력
        int n = s1.getNumber();
        System.out.println("학번: " + n);

        // getFamilyName(String name) 메소드 호출
        String familyName = Student.getFamilyName("홍,길동");
        System.out.println("성씨: " + familyName);

        // getYearString() 메소드 호출
        String yearString = s1.getYearString();
        System.out.println("입학년도: " + yearString);

        // 오버로드된 생성자 테스트
        Student s3 = new Student("김", "철수", 20190002);
        System.out.println(s3.getName()); // "김,철수"
        System.out.println(s3.getNumber()); // 20190002

        // 오버라이드된 getName() 메소드 사용 예제
        StudentExtended s4 = new StudentExtended("이,영희", 20210020);
        System.out.println(s4.getName()); // "영희"

    }
}

// getName() 메소드를 오버라이드하여 이름(given name)만 반환하는 서브클래스 예제
class StudentExtended extends Student {

    public StudentExtended(String name, int num) {
        super(name, num);
    }

    @Override
    public String getName() {
        return this.getGivenName();
    }
}
*/
