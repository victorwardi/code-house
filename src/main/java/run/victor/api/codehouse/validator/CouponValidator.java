package run.victor.api.codehouse.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import run.victor.api.codehouse.model.Coupon;
import run.victor.api.codehouse.neworder.NewPurchaseRequest;

/**
 * @author Victor Wardi - @victorwardi
 */
@Component
public class CouponValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> aClass) {
        return NewPurchaseRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NewPurchaseRequest newPurchaseRequest = (NewPurchaseRequest) o;
        if (newPurchaseRequest.hasCoupon()) {
            final Coupon coupon =  entityManager.find(Coupon.class, newPurchaseRequest.getCoupon());
            if (!coupon.isValid()) {
                errors.rejectValue("coupon", null, "Coupon must be a valid");
            }
        }
    }
}
