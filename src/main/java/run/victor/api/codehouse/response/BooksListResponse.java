package run.victor.api.codehouse.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import run.victor.api.codehouse.model.Book;

/**
 * @author Victor Wardi - @victorwardi
 */
public class BooksListResponse {

    private final List<Map<String, String>> books;

    public BooksListResponse(List<Book> books) {
        if(books == null){
            books = new ArrayList<>();
        }
        this.books = convertBooksToBooksListResponse(books);
    }

    private List<Map<String, String>> convertBooksToBooksListResponse(List<Book> books) {
        return books.stream()
            .map(b -> Map.of("id", b.getId().toString(), "title", b.getTitle()))
            .collect(Collectors.toList());
    }

    public List<Map<String, String>> getBooks() {
        return books;
    }
}
