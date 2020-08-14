package run.victor.api.codehouse.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import run.victor.api.codehouse.novopagamento.NewPaymentRequest;

/**
 * @author Victor Wardi - @victorwardi
 */
public class CheckDocumentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NewPaymentRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        NewPaymentRequest newPaymentRequest = (NewPaymentRequest) o;

      if(!newPaymentRequest.isDocumentValid()){
                errors.rejectValue("document", null, "Document must be a valid CPF or CPNJ");
      }


    }
}
