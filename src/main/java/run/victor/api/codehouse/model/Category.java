package run.victor.api.codehouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Entity to represent Category table
 * @author Victor Wardi - @victorwardi
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany
    Set<Book> books;

    /**
     * Construct for Mapping purposes
     * @deprecated
     */
    @Deprecated( since = "Used only por mapping")
    public Category() {
    }

    private Category(@NotBlank String name) {
        this.name = name;
    }

    /**
     * Create a new Category
     * @param name Category name
     * @return a new category
     */
    public static Category create(@NotBlank String name) {
        return new Category(name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
