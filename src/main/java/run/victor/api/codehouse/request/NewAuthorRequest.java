package run.victor.api.codehouse.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import run.victor.api.codehouse.model.Author;
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

    public static NewAuthorRequestBuilder builder() {
        return new NewAuthorRequestBuilder();
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

    public static class NewAuthorRequestBuilder {
        private @NotBlank String name;
        private @NotBlank @Email String email;
        private @NotBlank @Size(max = 400) String description;

        NewAuthorRequestBuilder() {
        }

        public NewAuthorRequestBuilder name(@NotBlank String name) {
            this.name = name;
            return this;
        }

        public NewAuthorRequestBuilder email(@NotBlank @Email String email) {
            this.email = email;
            return this;
        }

        public NewAuthorRequestBuilder description(@NotBlank @Size(max = 400) String description) {
            this.description = description;
            return this;
        }

        public NewAuthorRequest build() {
            return new NewAuthorRequest(name, email, description);
        }

        public String toString() {
            return "NewAuthorRequest.NewAuthorRequestBuilder(name=" + this.name + ", email=" + this.email + ", description=" + this.description + ")";
        }
    }
}
