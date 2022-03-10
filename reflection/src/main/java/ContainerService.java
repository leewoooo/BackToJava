import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

    public static <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType);

        // 필드를 돌면서 필드에 @Inject가 있는지 여부 확인 후 있다면 해당 filed의 type을 이용하여 인스턴스를 만들어준다.
        Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
            if (f.getAnnotation(Inject.class) != null) {
                f.setAccessible(true);
                Object fieldInstance = createInstance(f.getType());
                try {
                    f.set(instance, fieldInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return instance;
    }

    // 제네릭 method를 이용하여 class Type을 받아 인스턴스를 새로 생성 후 return
    // reflection을 이용하여 class에서 생성자 얻어오고 생성자에서 인스턴스 얻어옴.
    private static <T> T createInstance(Class<T> classType) {
        try {
            Constructor<T> constructor = classType.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
