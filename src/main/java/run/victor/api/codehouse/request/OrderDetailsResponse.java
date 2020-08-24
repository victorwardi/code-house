package run.victor.api.codehouse.request;

import java.math.BigDecimal;
import java.util.Set;

import run.victor.api.codehouse.model.Purchase;

/**
 * @author Victor Wardi - @victorwardi
 */
public class OrderDetailsResponse {

    private BigDecimal total;
    private BigDecimal totalWithDiscount;
    private ItemsOrderResponse itens;


    public OrderDetailsResponse(Purchase purchase) {
        this.total = purchase.getOrder().getTotal();
        if(purchase.getCouponApplied() != null){
            final BigDecimal discount = purchase.getCouponApplied().getDiscount();
            this.totalWithDiscount = applyDiscount(discount);
        }
        this.itens = new ItemsOrderResponse(purchase.getOrder().getItems());

    }

    private BigDecimal applyDiscount(BigDecimal discount) {
        final BigDecimal discountValueFromTotal = this.total.multiply(discount.divide(BigDecimal.valueOf(100)));
        return total.subtract(discountValueFromTotal).setScale(2);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public ItemsOrderResponse getItens() {
        return itens;
    }
}
