package run.victor.api.codehouse.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.util.Assert;
import run.victor.api.codehouse.model.Author;
import run.victor.api.codehouse.model.Category;
import run.victor.api.codehouse.validator.UniqueValue;


/**
 * Class to represent a new author request
 * @author Victor Wardi - @victorwardi
 */
public class NewAuthorRequest {

    @NotBlank
    private final String name;

    @NotBlank
    @Email
    @UniqueValue(domainClass = Author.class, fieldName = "email")
    private final String email;

    @NotBlank
    @Size(max = 400)
    private final String description;


    private NewAuthorRequest(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    /**
     * Create a new author request
     * @param name
     * @param email
     * @param description
     * @return NewAuthorRequest
     */
    public static NewAuthorRequest create(@NotBlank String name,
                                          @NotBlank @Email String email,
                                          @NotBlank @Size(max = 400) String description) {
           return new NewAuthorRequest(name, email, description);
    }

    public Author toModel(){
        return Author.create(name, email, description);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
