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


/**
 * Class create to represent a new author
 * @author Victor Wardi - @victorwardi
 */
public class NewAuthorRequest {

    @NotBlank
    private final String name;

    @NotBlank
    @Email
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
     * @return
     */
    public static NewAuthorRequest create(@NotBlank String name,
                                          @NotBlank @Email String email,
                                          @NotBlank @Size(max = 400) String description) {
        Assert.hasLength(name, "Name is required");
        Assert.hasLength(email, "Email is required");
        Assert.hasLength(description, "Description is required");
        return new NewAuthorRequest(name, email, description);
    }

    public Author toModel(){
        return Author.builder().name(name).email(email).description(description).build();
    }
}
