package run.victor.api.codehouse.response;

import run.victor.api.codehouse.model.Author;

/**
 * @author Victor Wardi - @victorwardi
 */
public class AuthorDetailResponse {

    private final String name;
    private final String email;
    private final String description;

    public AuthorDetailResponse(Author author) {
        this.name = author.getName();
        this.email = author.getEmail();
        this.description = author.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }


}
