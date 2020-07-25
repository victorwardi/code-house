package run.victor.api.houseofcode.controller;

import javax.validation.Valid;

import run.victor.api.houseofcode.model.Author;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.houseofcode.repository.AuthorRepository;

/**
 * @author Victor Wardi - @victorwardi
 */

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping
    public Author registerAuthor(@RequestBody @Valid Author author){
         return authorRepository.save(author);
    }
}
