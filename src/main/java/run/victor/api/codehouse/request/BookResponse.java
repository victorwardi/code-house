package run.victor.api.codehouse.request;

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
