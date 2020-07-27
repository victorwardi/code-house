package run.victor.api.codehouse.validator;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import run.victor.api.codehouse.model.Author;
import run.victor.api.codehouse.repository.AuthorRepository;
import run.victor.api.codehouse.request.NewAuthorRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author Victor Wardi - @victorwardi
 */
@ExtendWith(MockitoExtension.class)
class ProhibitsDuplicateAuthorEmailValidatorTest {

    @InjectMocks
    ProhibitsDuplicateAuthorEmailValidator prohibitsDuplicateAuthorEmailValidator;

    @Mock
    AuthorRepository authorRepository;

    @Test
    void shouldProhibitsDuplicateAuthorEmail() {
        //Mock a existing author with same email
        when(authorRepository.findByEmail(anyString())).thenReturn(Optional.of(new Author()));

        NewAuthorRequest newAuthorRequest = NewAuthorRequest.create("Bart", "email@mail.com", "description");
        Errors errors = new BeanPropertyBindingResult(newAuthorRequest, "newAuthorRequest");
        prohibitsDuplicateAuthorEmailValidator.validate(newAuthorRequest, errors);

        assertThat(errors.getFieldError("email").getDefaultMessage()).isEqualTo("Email must be unique.");
    }

    @Test
    void shouldAllowUniqueAuthorEmail() {
        //Mock empty return from repository, email is unique
        when(authorRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        NewAuthorRequest newAuthorRequest = NewAuthorRequest.create("Bart", "email@mail.com", "description");
        Errors errors = new BeanPropertyBindingResult(newAuthorRequest, "newAuthorRequest");
        prohibitsDuplicateAuthorEmailValidator.validate(newAuthorRequest, errors);

        assertThat(errors.getFieldError("email")).isNull();
    }
}