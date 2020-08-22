package run.victor.api.codehouse.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import run.victor.api.codehouse.neworder.NewPurchaseRequest;

/**
 * @author Victor Wardi - @victorwardi
 */
public class CheckDocumentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NewPurchaseRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        NewPurchaseRequest newPurchaseRequest = (NewPurchaseRequest) o;

      if(!newPurchaseRequest.isDocumentValid()){
                errors.rejectValue("document", null, "Document must be a valid CPF or CPNJ");
      }


    }
}
