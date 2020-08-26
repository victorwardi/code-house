package run.victor.api.codehouse.response;

import run.victor.api.codehouse.model.Category;

/**
 * @author Victor Wardi - @victorwardi
 */
public class CategoryDetailResponse {
    private final String name;

    public CategoryDetailResponse(Category category) {
        this.name = category.getName();
    }

    public String getName() {
        return name;
    }
}
