import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class App {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // Class 얻어오는 방법 1 -> 로딩 완료 된 인스턴스에서 얻어오기
        Class<BookImpl> bookClass = BookImpl.class;

        // Class 얻어오는 방법 2 -> 사용자가 만든 인스턴스에서 얻어오기
        BookImpl book = new BookImpl();
        Class<? extends BookImpl> bookClass2 = book.getClass();

        // Class 얻어오는 방법 3 -> full package 경로로 조회
        try {
            Class<?> bookClass3 = Class.forName("BookImpl");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 필드 조회 -> getFields()는 public한 필드만 받음.
        Arrays.stream(bookClass.getFields()).forEach(System.out::println);

        // 필드 조회 -> getDeclaredFields()를 해야 private, protected를 포함한 모든 필드를 확인할 수 있음.
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);

        // 메소드 조회 -> getMethods() public method만 조회가 되며 상속받은 method 또한 조회가 가능하다.
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);

        // 생성자 조회
        Arrays.stream(bookClass.getConstructors()).forEach(System.out::println);

        // 부모 조회
        String parentClassName = bookClass.getSuperclass().getCanonicalName();
        System.out.println("parentClassName = " + parentClassName); // Object

        // 인터페이스 조회
        Arrays.stream(bookClass.getInterfaces()).forEach(System.out::println); // Book

        // Annotation 조회
        Arrays.stream(bookClass.getAnnotations()).forEach(System.out::println);

        Arrays.stream(bookClass.getAnnotations()).forEach(annotation -> {
            // Annotation을 조회한 후 해당 Annotation의 type을 확인 후 해당 Annotation의 속성들을 사용할 수 있음.
            if (annotation instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                System.out.println("myAnnotation.age() = " + myAnnotation.age()); // 27
                System.out.println("myAnnotation.name() = " + myAnnotation.name()); // foobar
            }
        });


        // ================ 리플렉션을 이용한 인스턴스 조작 ================
        Class<Member> memberClass = Member.class;

        // 인스턴스 만들기 (1. 생성자 가져오기 2. 생성자를 통해 인스턴스 만들기))
        // 1. 기본 생성자
        Constructor<Member> noArgConstructor = memberClass.getConstructor(null);
        Member member = noArgConstructor.newInstance();
        System.out.println("member = " + member);


        Field name = memberClass.getField("NAME");

        // static이기 때문에 특정 인스턴스를 지정할 필요가 없음
        String nameField = (String) name.get(null);
        System.out.println("nameField = " + nameField); // foobar

        // static 변수 가져오기 및 변경하기
        name.set(null, "LEEWOOOO");
        String changedName = (String) name.get(null);
        System.out.println("changedName = " + changedName); // LEEWOOOO

        // 2. 파라미터가 있는 생성자 가져오기
        Constructor<Member> argConstructor = memberClass.getConstructor(int.class);
        Member memberWithAge = argConstructor.newInstance(27);
        System.out.println("memberWithAge = " + memberWithAge);

        // private 변수 가져오기 (private 필드에 접근하려면 접근 권한 부여 및 인스턴스 생성시 초기화 되는 private변수는 인스턴스 까지 같이 부여해야함.)
        // private field를 조회하려면 getDeclaredField()를 이용
        Field ageField = memberClass.getDeclaredField("age");
        ageField.setAccessible(true);
        int age = (int) ageField.get(memberWithAge);
        System.out.println("age = " + age);

        ageField.set(memberWithAge, 99);
        int changeAge = (int) ageField.get(memberWithAge);
        System.out.println("changeAge = " + changeAge); // 99

        // 3. method 호출하기
        // method를 가져올 때 method명, 파라미터 타입을 지정해야함
        Method sum = memberClass.getMethod("sum", int.class, int.class);

        // 실행하기 (실행할 때 static method가 아닌 이상 인스턴스를 지정해야 한다.)
        int result = (int) sum.invoke(memberWithAge, 1, 2);
        System.out.println("result = " + result);
    }
}
