package me.leewoooo.dynamicproxy;

public class JavaProxyExample {

    public static void main(String[] args) {
        BookServiceImplProxy bookServiceImplProxy = new BookServiceImplProxy(new BookServiceImpl());
        bookServiceImplProxy.printTitle(new Book("foobar"));
    }
}
