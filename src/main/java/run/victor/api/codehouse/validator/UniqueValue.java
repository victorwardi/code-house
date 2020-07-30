package run.victor.api.codehouse.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Victor Wardi - @victorwardi
 */
@Documented
@Constraint(validatedBy = UniqueValueValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface UniqueValue {

    String message() default "Field must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();
}