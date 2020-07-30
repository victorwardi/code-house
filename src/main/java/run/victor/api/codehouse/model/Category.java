package run.victor.api.codehouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * @author Victor Wardi - @victorwardi
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Deprecated
    public Category() {
    }

    private Category(@NotBlank String name) {
        this.name = name;
    }

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
