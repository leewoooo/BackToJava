import java.lang.annotation.*;

// Annotation은 주석과 동일하게 처리되기 source와 class에서 남지만
// 바이트 코드를 로딩하였을 때 메모리 상에서는 남지 않는다. 하지만 읽어오고 싶다면 @Retention(RetentionPolicy.RUNTIME)를 부여해야 한다.
// @Inherited option을 부여하면 해당 Annotation을 사용하는 class의 자식 class에서 부모 class의 Annotation까지 조회가 가능.
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
@Inherited
public @interface MyAnnotation {

    //Annotation은 값들을 가질 수 있으며 default를 지정하지 않으면 Annotation을 사용하는 곳에서 값 지정을 강제한다.
    String name() default "foobar";
    int age();
}
