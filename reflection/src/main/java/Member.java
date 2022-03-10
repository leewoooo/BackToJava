public class Member {

    public static String NAME = "foobar";

    private int age;

    public Member() {
    }

    public Member(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public int sum(int left, int right){
        return left + right;
    }
}
