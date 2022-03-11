package me.leewoooo.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyCGLIB {

    public static void main(String[] args) {
        MethodInterceptor handler = new MethodInterceptor() {
            BookService bookService = new BookServiceImpl();
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("prev invoke");
                Object invoke = method.invoke(bookService, args);
                System.out.println("after invoke");
                return invoke;
            }
        };

        BookService bookService = (BookService) Enhancer.create(BookService.class, handler);

        // bookService.getClass() = class me.leewoooo.dynamicproxy.BookService$$EnhancerByCGLIB$$23e002dc
        System.out.println("bookService.getClass() = " + bookService.getClass());
        
        bookService.printTitle(new Book("leewoooo"));
    }
}
