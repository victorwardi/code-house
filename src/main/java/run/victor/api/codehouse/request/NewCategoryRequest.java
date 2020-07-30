package run.victor.api.codehouse.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import run.victor.api.codehouse.model.Category;
import run.victor.api.codehouse.validator.UniqueValue;

/**
 * * Class represent a new category request
 *
 * @author Victor Wardi - @victorwardi
 */
public class NewCategoryRequest {

    @NotBlank()
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private final String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    private NewCategoryRequest(String name) {
        this.name = name;
    }

    /**
     * Create a new author request
     *
     * @param name
     * @return NewAuthorRequest
     */
    public static NewCategoryRequest create(@NotBlank String name) {
        return new NewCategoryRequest(name);
    }

    public Category toModel() {
        return Category.create(name);
    }

    public String getName() {
        return name;
    }

}
