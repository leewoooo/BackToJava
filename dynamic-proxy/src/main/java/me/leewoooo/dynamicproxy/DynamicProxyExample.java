package me.leewoooo.dynamicproxy;

import java.lang.reflect.Proxy;

public class DynamicProxyExample {

    public static void main(String[] args) {
        // interface 기반으로 해당 구현체의 인스턴스를 생성하였다. (pure 자바에서 제공하는 reflect. Proxy는 Class 기반의 Proxy를 만들지 못한다.)

        // 하지만 여기서 Class기반으로 인스턴스를 생성하려고 하면 컴파일 오류가 난다.
        // Exception in thread "main" java.lang.IllegalArgumentException: me.leewoooo.dynamicproxy.BookServiceImpl is not an interface
        // BookServiceImpl bookService = (BookServiceImpl) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookServiceImpl.class}, (proxy, method, arguments) -> {

        BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class}, (proxy, method, arguments) -> {
            BookService realSubject = new BookServiceImpl();
            System.out.println("prev method invoke");
            Object invoke = method.invoke(realSubject, arguments);
            System.out.println("after method invoke");
            return invoke;
        });
        System.out.println("bookService.getClass() = " + bookService.getClass()); // class com.sun.proxy.$Proxy0

        Book book = new Book("foobar");
        bookService.printTitle(book);
    }
}
