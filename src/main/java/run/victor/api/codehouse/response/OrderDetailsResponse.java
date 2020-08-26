package run.victor.api.codehouse.response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.stream.Collectors;

import run.victor.api.codehouse.model.Order;
import run.victor.api.codehouse.model.Purchase;

/**
 * @author Victor Wardi - @victorwardi
 */
public class OrderDetailsResponse {

    private final BigDecimal total;
    private final BigDecimal totalWithDiscount;
    private final Set<ItemOrderResponse> items;


    public OrderDetailsResponse(Purchase purchase) {
        this.total = purchase.getOrder().getTotal();
        if (purchase.getCouponApplied() != null) {
            final BigDecimal discount = purchase.getCouponApplied().getDiscount();
            this.totalWithDiscount = applyDiscount(discount);
        }else{
            totalWithDiscount = null;
        }

        this.items = getItemsFromOrder(purchase.getOrder());

    }

    private BigDecimal applyDiscount(BigDecimal discount) {
        final BigDecimal discountValueFromTotal = this.total.multiply(discount.divide(BigDecimal.valueOf(100 )));
        return total.subtract(discountValueFromTotal).setScale(2, RoundingMode.CEILING);
    }

    private Set<ItemOrderResponse> getItemsFromOrder(Order order) {
        return order.getItems().stream().map(ItemOrderResponse::fromModel).collect(Collectors.toSet());
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public Set<ItemOrderResponse> getItems() {
        return items;
    }
}
