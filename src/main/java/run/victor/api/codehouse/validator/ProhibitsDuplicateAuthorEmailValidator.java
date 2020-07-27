package run.victor.api.codehouse.validator;

import java.util.Optional;

import org.springframework.context.MessageSource;
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

    private final MessageSource messageSource;

    public ProhibitsDuplicateAuthorEmailValidator(AuthorRepository authorRepository, MessageSource messageSource) {
        this.authorRepository = authorRepository;
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NewAuthorRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NewAuthorRequest author = (NewAuthorRequest) o;
        Optional<Author> optionalAuthor = authorRepository.findByEmail(author.getEmail());
        if (optionalAuthor.isPresent()) {
            errors.rejectValue("email", "101", "Email must be unique.");
        }
    }
}
