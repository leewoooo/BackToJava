package me.leewoooo.dynamicproxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ProxyByteBuddy {

    public static void main(String[] args) throws Exception {
        // get Class
        DynamicType.Builder<BookServiceImpl> subclass = new ByteBuddy().subclass(BookServiceImpl.class);
        DynamicType.Loaded<BookServiceImpl> load = subclass
                .method(named("printTitle")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    BookServiceImpl bookService = new BookServiceImpl();
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("prev invoke");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("after invoke");
                        return invoke;
                    }
                }))
                .make().load(BookServiceImpl.class.getClassLoader());
        Class<? extends BookServiceImpl> bookServiceImplClass = load.getLoaded();

        // make instace
        BookService bookService = bookServiceImplClass.getConstructor().newInstance();

        // bookService.getClass() = class me.leewoooo.dynamicproxy.BookServiceImpl$ByteBuddy$HF4myKM3
        System.out.println("bookService.getClass() = " + bookService.getClass());
        
        bookService.printTitle(new Book("leewoooo"));
    }
}
