package run.victor.api.codehouse.neworder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.codehouse.model.Purchase;
import run.victor.api.codehouse.validator.CheckDocumentValidator;

/**
 * @author Victor Wardi - @victorwardi
 */
@RestController
@RequestMapping("/v1/purchases")
public class NewPurchasesController {

    @PersistenceContext
    private EntityManager entityManager;

    private final StateBelongsToCountryValidator stateBelongsToCountryValidator;
    private final OrderTotalValidator orderTotalValidator;

    public NewPurchasesController(StateBelongsToCountryValidator stateBelongsToCountryValidator, OrderTotalValidator orderTotalValidator) {
        this.stateBelongsToCountryValidator = stateBelongsToCountryValidator;
        this.orderTotalValidator = orderTotalValidator;
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new CheckDocumentValidator());
        webDataBinder.addValidators(orderTotalValidator);
        webDataBinder.addValidators(stateBelongsToCountryValidator);
    }

    @PostMapping
    @Transactional
    public String registerPurchase(@RequestBody @Valid NewPurchaseRequest newPurchaseRequest) {
        Purchase purchase = newPurchaseRequest.toModel(entityManager);
        entityManager.persist(purchase);
        return purchase.toString();
    }
}
