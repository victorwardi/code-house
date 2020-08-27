package run.victor.api.codehouse.request;

import java.util.List;

import org.junit.jupiter.api.Test;
import run.victor.api.codehouse.model.Book;
import run.victor.api.codehouse.response.BooksListResponse;

/**
 * @author Victor Wardi - @victorwardi
 */
class BooksResponseTest {

    //@Test
    void whenBooksListValid_thenReturnBooksResponse() {
        List<Book> books = List.of(
            Book.builder().id(1L).title("Java 15").build(),
            Book.builder().id(2L).title("Spring MVC").build(),
            Book.builder().id(3L).title("Design Efficient").build(),
            Book.builder().id(4L).title("Java PRO").build()
        );
        new BooksListResponse(books).getBooks().forEach(System.out::println);
    }


}