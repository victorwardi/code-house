package run.victor.api.codehouse.neworder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;
import run.victor.api.codehouse.model.ItemOrder;
import run.victor.api.codehouse.model.Order;
import run.victor.api.codehouse.model.Purchase;
import run.victor.api.codehouse.repository.BookRepository;

/**
 * @author Victor Wardi - @victorwardi
 */
public class NewOrderRequest {

    @NotNull
    @Positive
    @Digits(integer = 6, fraction = 2)
    private final BigDecimal total;

    @NotEmpty
    @Size(min = 1)
    @Valid
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private final List<NewOrderItemRequest> itens;

    public NewOrderRequest(BigDecimal total, List<NewOrderItemRequest> itens) {
        this.total = total;
        this.itens = itens;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<NewOrderItemRequest> getItens() {
        return itens;
    }

    public boolean checkTotal(BookRepository bookRepository) {
        final BigDecimal totalFromServer = itens.stream().map(item -> item.getItemTotal(bookRepository)).reduce(BigDecimal.ZERO, BigDecimal::add);
        return total.compareTo(totalFromServer) == 0;
    }

    public Function<Purchase, Order> toModel(EntityManager manager) {
        Set<ItemOrder> itemOrders = itens.stream().map(item -> item.toModel(manager)).collect(Collectors.toSet());

        return (purchase) -> {
            Order order = new Order(purchase, itemOrders);
            Assert.isTrue(order.checkTotal(total), "Total is inconsistent");
            return order;
        };
    }
}
