package run.victor.api.codehouse.novopagamento;

import javax.validation.Valid;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.codehouse.validator.CheckDocumentValidator;

/**
 * @author Victor Wardi - @victorwardi
 */
@RestController
@RequestMapping("/v1/payments")
public class NewPaymentsControllers {

    private final StateBelongsToCountryValidator stateBelongsToCountryValidator;

    public NewPaymentsControllers(StateBelongsToCountryValidator stateBelongsToCountryValidator) {
        this.stateBelongsToCountryValidator = stateBelongsToCountryValidator;
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new CheckDocumentValidator());
        webDataBinder.addValidators(stateBelongsToCountryValidator);
    }

    @PostMapping
    public String registerPayment(@RequestBody @Valid NewPaymentRequest newPaymentRequest) {
        return "new payment...";
    }
}
