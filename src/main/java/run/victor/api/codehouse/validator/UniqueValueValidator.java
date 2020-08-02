package run.victor.api.codehouse.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Victor Wardi - @victorwardi
 */
@Component
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> aClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue uniqueValue) {
        domainAttribute = uniqueValue.fieldName();
        aClass = uniqueValue.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null ||  (value instanceof String && ((String) value).isBlank())) {
            return true;
        }
        Query query = entityManager.createQuery("SELECT 1 FROM " + aClass.getName() + " WHERE " + domainAttribute + " = :value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "More than one record of " + aClass + " with attribute "+ domainAttribute + " equal: '" + value + "' was found.");
        return list.isEmpty();
    }
}
