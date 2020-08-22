package run.victor.api.codehouse.neworder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import run.victor.api.codehouse.model.Country;
import run.victor.api.codehouse.model.State;

/**
 * @author Victor Wardi - @victorwardi
 */
@Component
public class StateBelongsToCountryValidator implements Validator {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean supports(Class<?> aClass) {
        return NewPurchaseRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        final NewPurchaseRequest newPurchaseRequest = (NewPurchaseRequest) o;

        if (newPurchaseRequest.hasState()) {
            final Country country = entityManager.find(Country.class, newPurchaseRequest.getCountryId());
            final State state = entityManager.find(State.class, newPurchaseRequest.getStateId());

            if (!state.belongToCountry(country)) {
                errors.rejectValue("stateId", null, "State does not belong to country");
            }
        }
    }
}
