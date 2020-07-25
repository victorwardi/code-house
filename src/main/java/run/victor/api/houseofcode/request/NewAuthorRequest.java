package run.victor.api.houseofcode.request;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import run.victor.api.houseofcode.model.Author;


/**
 * @author Victor Wardi - @victorwardi
 */
@Data
@Builder
@AllArgsConstructor
public class NewAuthorRequest {

    @NotBlank( message = "is required")
    private final String name;

    @NotBlank( message = "is required")
    @Email
    private final String email;

    @NotBlank( message = "is required")
    @Size(max = 400)
    private final String description;

    public Author toModel(){
        return Author.builder().name(name).email(email).description(description).build();
    }
}
