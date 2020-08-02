package run.victor.api.codehouse.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

import org.springframework.util.Assert;

/**
 * @author Victor Wardi - @victorwardi
 */
public class IdExistsValidator implements ConstraintValidator<IdExists, Object> {

    private String domainAttribute;
    private Class<?> aClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(IdExists idExists) {
        domainAttribute = idExists.fieldName();
        aClass = idExists.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        Query query = entityManager.createQuery("SELECT 1 FROM " + aClass.getName() + " WHERE " + domainAttribute + " = :value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "One record of " + aClass + " with attribute "+ domainAttribute + " equal : '" + value + "' must exists.");
        return !list.isEmpty();
    }
}
