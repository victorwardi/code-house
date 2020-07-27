package run.victor.api.codehouse.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import run.victor.api.codehouse.model.Author;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.codehouse.request.NewAuthorRequest;

/**
 * @author Victor Wardi - @victorwardi
 */

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public Author registerAuthor(@RequestBody @Valid NewAuthorRequest newAuthorRequest) {
        Author author = newAuthorRequest.toModel();
        return entityManager.merge(author);
    }
}
