package run.victor.api.codehouse.neworder;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import run.victor.api.codehouse.repository.BookRepository;

/**
 * @author Victor Wardi - @victorwardi
 */
@Component
public class OrderTotalValidator implements Validator {

    private final BookRepository bookRepository;

    public OrderTotalValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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
        final boolean totalOrderIsConsistent = newPurchaseRequest.getOrder().checkTotal(bookRepository);
        if (!totalOrderIsConsistent) {
            errors.rejectValue("order.total", null, "Order Total is inconsistent");
        }
    }
}

