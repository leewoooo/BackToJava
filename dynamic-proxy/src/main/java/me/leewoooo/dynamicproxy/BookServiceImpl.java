package me.leewoooo.dynamicproxy;

public class BookServiceImpl implements BookService{

    @Override
    public void printTitle(Book book) {
        System.out.println("book.getTitle() = " + book.getTitle());
    }
}
