package run.victor.api.codehouse.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.codehouse.model.Book;
import run.victor.api.codehouse.request.NewBookRequest;

/**
 * @author Victor Wardi - @victorwardi
 */
@RestController
@RequestMapping("/v1/books")
public class BooksController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public Book registerBook(@RequestBody @Valid NewBookRequest newBookRequest) {
        Book book = newBookRequest.toModel(entityManager);
        entityManager.persist(book);
        return book;
    }
}