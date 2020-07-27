package run.victor.api.codehouse.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import run.victor.api.codehouse.model.Author;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.codehouse.request.NewAuthorRequest;
import run.victor.api.codehouse.validator.ProhibitsDuplicateAuthorEmailValidator;

/**
 * @author Victor Wardi - @victorwardi
 */
@RestController
@RequestMapping("/v1/authors")
//2
public class AuthorController {

    private final EntityManager entityManager;
    private final ProhibitsDuplicateAuthorEmailValidator prohibitsDuplicateAuthorEmailValidator;

    public AuthorController(EntityManager entityManager, ProhibitsDuplicateAuthorEmailValidator prohibitsDuplicateAuthorEmailValidator) {
        this.entityManager = entityManager;
        this.prohibitsDuplicateAuthorEmailValidator = prohibitsDuplicateAuthorEmailValidator;
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(prohibitsDuplicateAuthorEmailValidator);
    }

    @PostMapping
    @Transactional
    public Author registerAuthor(@RequestBody @Valid NewAuthorRequest newAuthorRequest) {
        Author author = newAuthorRequest.toModel();
        entityManager.persist(author);
        return author;
    }
}
