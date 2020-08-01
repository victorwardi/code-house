package run.victor.api.codehouse.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.codehouse.model.Book;
import run.victor.api.codehouse.model.Category;
import run.victor.api.codehouse.repository.CategoryRepository;
import run.victor.api.codehouse.request.NewCategoryRequest;

/**
 * Controller of
 * @author Victor Wardi - @victorwardi
 */
@RestController
@RequestMapping("/v1/categories")
public class CategoriesController {

    private final CategoryRepository categoryRepository;

    public CategoriesController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public Category registerCategory(@RequestBody @Valid NewCategoryRequest newCategoryRequest) {
        Category category = newCategoryRequest.toModel();
        categoryRepository.save(category);
        return category;
    }

    @GetMapping()
    public Iterable<Category> findAll() {
        return  categoryRepository.findAll();
    }
}
