package me.leewoooo.dynamicproxy;

public class BookServiceImplProxy implements BookService{

    private BookService bookService;

    public BookServiceImplProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void printTitle(Book book) {
        System.out.println("prev prev method");
        bookService.printTitle(book);
        System.out.println("prev after method");
    }
}
