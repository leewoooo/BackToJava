@MyAnnotation(age = 27)
public class BookImpl implements Book{

    private String a = "a";

    private static String B = "B";

    protected String c = "c";

    protected static String D = "D";

    public String e = "E";

    public BookImpl() {
    }

    public BookImpl(String a, String c, String e) {
        this.a = a;
        this.c = c;
        this.e = e;
    }

    public String getA() {
        return a;
    }

    public static String getB() {
        return B;
    }

    public String getC() {
        return c;
    }

    public static String getD() {
        return D;
    }

    public String getE() {
        return e;
    }
}
