package run.victor.api.codehouse.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.codehouse.model.Category;
import run.victor.api.codehouse.request.NewCategoryRequest;

/**
 * Controller of
 * @author Victor Wardi - @victorwardi
 */
@RestController
@RequestMapping("/v1/categories")
public class CategoriesController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public Category registerCategory(@RequestBody @Valid NewCategoryRequest newCategoryRequest) {
        Category category = newCategoryRequest.toModel();
        entityManager.persist(category);
        return category;
    }
}
