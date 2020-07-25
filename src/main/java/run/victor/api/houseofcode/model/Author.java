package run.victor.api.houseofcode.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


/**
 * @author Victor Wardi - @victorwardi
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank( message = "is required")
    private String name;

    @NotBlank( message = "is required")
    @Email
    private String email;

    @Size(max = 400)
    private String description;

    @CreationTimestamp
    private LocalDateTime createDateTime;

}
