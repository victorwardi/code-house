package run.victor.api.codehouse.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import run.victor.api.codehouse.model.Author;
import run.victor.api.codehouse.repository.AuthorRepository;
import run.victor.api.codehouse.request.NewAuthorRequest;

/**
 * Validator to check if author's email is unique.
 *
 * @author Victor Wardi - @victorwardi
 */
@Component
public class ProhibitsDuplicateAuthorEmailValidator implements Validator {

    private final AuthorRepository authorRepository;

    public ProhibitsDuplicateAuthorEmailValidator(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return NewAuthorRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //Only validates email if default properties have been checked. and there is no more erros
        if(errors.hasErrors()){
            return;
        }
        NewAuthorRequest author = (NewAuthorRequest) o;
        Optional<Author> optionalAuthor = authorRepository.findByEmail(author.getEmail());
        if (optionalAuthor.isPresent()) {
            errors.rejectValue("email", "101", "Email must be unique.");
        }
    }
}
