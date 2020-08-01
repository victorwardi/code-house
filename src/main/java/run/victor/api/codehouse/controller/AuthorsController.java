package run.victor.api.codehouse.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import run.victor.api.codehouse.model.Author;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.codehouse.repository.AuthorRepository;
import run.victor.api.codehouse.request.NewAuthorRequest;

/**
 * @author Victor Wardi - @victorwardi
 */
@RestController
@RequestMapping("/v1/authors")
//1
public class AuthorsController {

    private final AuthorRepository authorRepository;

    public AuthorsController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping
    public Author registerAuthor(@RequestBody @Valid NewAuthorRequest newAuthorRequest) {
        Author author = newAuthorRequest.toModel();
        authorRepository.save(author);
        return author;
    }

    @GetMapping()
    public  Iterable<Author> findAll() {
        return authorRepository.findAll();
    }
}
