package run.victor.api.codehouse.model;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;
import run.victor.api.codehouse.exception.UniqueEmailException;


/**
 * Entity to Author table.
 *
 * @author Victor Wardi - @victorwardi
 */
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "author")
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
    private LocalDateTime createDateTime;
}
