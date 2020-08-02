package run.victor.api.codehouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;


/**
 * Entity to Author table.
 *
 * @author Victor Wardi - @victorwardi
 */
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 400)
    private String description;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDateTime;

    @OneToMany
    Set<Book> books;

    @Deprecated
    public Author() {
    }

    private Author(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public static Author create(@NotBlank String name, @NotBlank String email, @NotBlank @Size(max = 400) String description) {
       return new Author(name, email, description);
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }
}
