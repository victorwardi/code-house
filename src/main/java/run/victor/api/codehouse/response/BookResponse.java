package run.victor.api.codehouse.response;

import run.victor.api.codehouse.model.Book;

/**
 * @author Victor Wardi - @victorwardi
 */
public class BookResponse {

    private final Long id;
    private final String title;

    public BookResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public static BookResponse fromModel(Book book) {
        return new BookResponse(book.getId(), book.getTitle());
    }

    @Override
    public String toString() {
        return "BookResponse{" + "id=" + id + ", title='" + title + '\'' + '}';
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
