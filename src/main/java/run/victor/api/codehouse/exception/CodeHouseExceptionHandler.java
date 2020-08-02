package run.victor.api.codehouse.exception;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Victor Wardi - @victorwardi
 */
@RestControllerAdvice
public class CodeHouseExceptionHandler {

    private final MessageSource messageSource;

    public CodeHouseExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return getErrors(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UniqueEmailException.class)
    public Map<String, String> handleUniqueEmailException(UniqueEmailException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error:", ex.getLocalizedMessage());
        return errors;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Map<String, String> handleValidationException(ValidationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error:", ex.getLocalizedMessage());
        return errors;
    }

    private Map<String, String> getErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
