import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerServiceTest {

    @Test
    @DisplayName("class type을 이용하여 object를 가져옴")
    void getObject_Repository(){
        //given
        //when
        BookRepository bookRepository = ContainerService.getObject(BookRepository.class);

        //then
        Assertions.assertNotNull(bookRepository);
    }

    @Test
    @DisplayName("class type을 이용하여 object를 가져옴 - book Repository, book Service")
    void getObject_BookService(){
        //given
        //when
        BookService bookService = ContainerService.getObject(BookService.class);

        //then
        Assertions.assertNotNull(bookService);
        Assertions.assertNotNull(bookService.bookRepository);

        System.out.println("bookService = " + bookService);
        System.out.println("bookService.bookRepository = " + bookService.bookRepository);
    }
}